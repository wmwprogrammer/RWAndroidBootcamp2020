package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MovieRepositoryImpl
import com.wmw.movieviewer.repository.UserRepositoryImpl
import kotlinx.coroutines.launch

class MovieViewModel(
        private val repository: MovieRepositoryImpl,
        private val userRepository: UserRepositoryImpl
) : ViewModel() {

    private var currentPage = 1

    fun getMovies(): LiveData<List<Movie>> = repository.getAllMovies()

    fun fetchMovies() {
        viewModelScope.launch {
            val endingPage = currentPage + 9
            repository.loadMoviesForPage(currentPage, endingPage)
            currentPage++
        }
    }

    fun logOut() {
        userRepository.setUserLoggedIn(false)
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }
}