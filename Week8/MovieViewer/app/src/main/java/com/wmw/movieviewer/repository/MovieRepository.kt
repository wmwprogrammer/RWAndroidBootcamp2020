package com.wmw.movieviewer.repository

import androidx.lifecycle.LiveData
import com.wmw.movieviewer.App
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.model.MovieDao
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.networking.RemoteApi

class MovieRepository(private val movieDao: MovieDao, private val movieApi: RemoteApi) {

    fun getAllMovies(): LiveData<List<Movie>> = dao.getAllMoviesSortedByTitle()

    suspend fun refreshMovies() {
        val result = movieApi.getTopMovies()

        if (result is Success) {
            movieDao.insert(result.data.data.movies.toList().map {
                Movie(it.idIMDB, it.releaseDate, it.title, it.plot, it.genres, it.urlPoster)
            })
        } else {
            print("Error: $result")
        }
    }

    suspend fun deleteMovieById(movieId: String) = dao.deleteMovieById(movieId)

    suspend fun getMovieById(movieId: String?) = dao.getMovieById(movieId)

    private val dao = App.movieDb.movieDao()
}
