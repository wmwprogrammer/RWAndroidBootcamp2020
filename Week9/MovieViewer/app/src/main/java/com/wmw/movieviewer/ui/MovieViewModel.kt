package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository,
    private val userRepository: UserRepository
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