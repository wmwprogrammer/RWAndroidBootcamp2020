package com.wmw.movieviewer.repository

import androidx.lifecycle.LiveData
import com.wmw.movieviewer.model.Movie
import org.koin.core.KoinComponent

interface MoviesRepository : KoinComponent {
    fun getAllMovies(): LiveData<List<Movie>>

    suspend fun getMovieById(movieId: String?): Movie

    suspend fun loadMoviesForPage(startingPage: Int = 1, endingPage: Int = 10)

    suspend fun deleteMovie(movie: Movie)
}