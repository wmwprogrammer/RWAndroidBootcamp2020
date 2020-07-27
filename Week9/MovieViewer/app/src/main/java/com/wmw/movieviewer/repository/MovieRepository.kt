package com.wmw.movieviewer.repository

import androidx.lifecycle.LiveData
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.model.MovieDao
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.model.response.MovieResponse
import com.wmw.movieviewer.networking.RemoteApi

open class MovieRepository(private val movieDao: MovieDao, private val movieApi: RemoteApi) {
    fun getAllMovies(): LiveData<List<Movie>> = movieDao.getAllMoviesSortedByTitle()

    suspend fun loadMoviesForPage(startingPage: Int = 1, endingPage: Int = 10) {

        val moviesList = movieApi.getTopMovies(startingPage, endingPage)

        if (moviesList is Success) {
            saveMoviesToDatabase(moviesList.data.data.movies.toList())
        }
    }


    private suspend fun saveMoviesToDatabase(movies: List<MovieResponse>) {
        if (movies.isNotEmpty()) {
            movieDao.insert(movies.map {
                Movie(
                    it.idIMDB,
                    it.releaseDate,
                    it.title,
                    it.plot,
                    it.genres,
                    it.urlPoster,
                    it.ranking
                )
            })
        }
    }

    suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

    suspend fun getMovieById(movieId: String?) = movieDao.getMovieById(movieId)

}
