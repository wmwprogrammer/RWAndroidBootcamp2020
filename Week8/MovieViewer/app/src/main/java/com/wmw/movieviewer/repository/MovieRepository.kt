package com.wmw.movieviewer.repository

import com.wmw.movieviewer.App
import com.wmw.movieviewer.model.Movie

class MovieRepository {
//    private var allLiveMovies: LiveData<List<Movie>> = dao.getAllMoviesSortedByTitle()

    suspend fun deleteMovieById(movieId: String) = dao.deleteMovieById(movieId)

    suspend fun getAllMovies(): List<Movie> = dao.getAllMoviesSortedByTitle()

    suspend fun getMovieById(movieId: String?) = dao.getMovieById(movieId)

//    suspend fun storeMoviesIfNotEmpty(movies: List<Movie>) {
//        val movieList = getAllMovies().value
//        if (movieList != null && movieList.count() < 1) saveMovies(movies)
//    }

    suspend fun insertMovie(movie: Movie) = dao.insert(movie)

    private val dao = App.movieDb.movieDao()
//    private suspend fun saveMovies(movies: List<Movie>) = dao.insert(movies)
}
