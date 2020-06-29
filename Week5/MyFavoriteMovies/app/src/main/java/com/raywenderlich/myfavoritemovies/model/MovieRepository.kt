package com.raywenderlich.myfavoritemovies.model

import androidx.lifecycle.LiveData

interface MovieRepository {
    fun addMovie(movie: Movie)
    fun getMovies(): LiveData<List<Movie>>
    fun deleteMovies(movie: Movie)
    fun deleteAllMovies()
}