package com.wmw.movieviewer.repository

import com.wmw.movieviewer.App
import com.wmw.movieviewer.model.Movie

class MovieRepository {

    suspend fun deleteMovieById(movieId: String) = dao.deleteMovieById(movieId)

    suspend fun getAllMovies(): List<Movie> = dao.getAllMovies()

    suspend fun getMovieById(movieId: String?) = dao.getMovieById(movieId)

    suspend fun storeMoviesIfNotEmpty(movies: List<Movie>) {
        if (getAllMovies().count() < 1) saveMovies(movies)
    }

    suspend fun insertMovie(movie: Movie) = dao.insertMovie(movie)

    private val dao = App.movieDb.movieDao()
    private suspend fun saveMovies(movies: List<Movie>) = dao.insertMovies(movies)
}
