package com.wmw.movieviewer.repository

import androidx.lifecycle.LiveData
import com.wmw.movieviewer.model.Movie

interface MovieRepository {
    fun getAllMovies(): LiveData<List<Movie>>

    suspend fun loadMoviesForPage(startingPage: Int = 1, endingPage: Int = 10)

    suspend fun deleteMovie(movie: Movie)
}