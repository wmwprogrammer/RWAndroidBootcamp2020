package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private val selectedLiveMovie = MutableLiveData<Movie>()

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            selectedLiveMovie.value = movie
        }
    }

    fun getDetailMovieLiveData(): LiveData<Movie> = selectedLiveMovie
}