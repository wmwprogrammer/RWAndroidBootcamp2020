package com.raywenderlich.myfavoritemovies.model

import com.raywenderlich.myfavoritemovies.App

class MovieRepository {

    private val dao = App.movieDb.movieDao()

    fun getAllMovies(): List<Movie> = dao.getAllMovies()

    fun getMovieById(movieId: Int?) = dao.getMovieById(movieId)

    private fun saveMovies(movies: List<Movie>) = dao.insertMovies(movies)

    fun storeMoviesIfNotEmpty(movies: List<Movie>) {
        if (getAllMovies().count() < 1) saveMovies(movies)
    }

//    fun addMovie(movie: Movie)
//    fun getAllMovies(): LiveData<List<Movie>>
//    fun deleteMovies(movie: Movie)
//    fun deleteAllMovies()
}