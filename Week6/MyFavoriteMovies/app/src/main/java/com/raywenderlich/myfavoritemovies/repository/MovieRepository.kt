package com.raywenderlich.myfavoritemovies.repository

import com.raywenderlich.myfavoritemovies.App
import com.raywenderlich.myfavoritemovies.model.Movie

class MovieRepository {

    fun deleteMovieById(movieId: Int) = dao.deleteMovieById(movieId)

    fun getAllMovies(): List<Movie> = dao.getAllMovies()

    fun getMovieById(movieId: Int?) = dao.getMovieById(movieId)

    fun storeMoviesIfNotEmpty(movies: List<Movie>) {
        if (getAllMovies().count() < 1) saveMovies(movies)
    }

    private val dao = App.movieDb.movieDao()
    private fun saveMovies(movies: List<Movie>) = dao.insertMovies(movies)
}
