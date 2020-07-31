package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wmw.movieviewer.model.Movie

class MovieDetailViewModel : ViewModel() {
    private val selectedLiveMovie = MutableLiveData<Movie>()

    fun setDetailMovie(movie: Movie?) {
        selectedLiveMovie.value = movie
    }

    fun getDetailMovieLiveData(): LiveData<Movie> = selectedLiveMovie
}