package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailViewModel() : ViewModel(), KoinComponent {
    private val selectedLiveMovie = MutableLiveData<Movie>()
    private val repository by inject<MoviesRepository>()

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            selectedLiveMovie.value = movie
        }
    }

    fun getDetailMovieLiveData(): LiveData<Movie> = selectedLiveMovie
}