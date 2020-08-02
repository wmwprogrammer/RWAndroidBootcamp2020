package com.wmw.movieviewer.repository

import androidx.lifecycle.LiveData
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.model.MovieDao
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.model.response.MovieResponse
import com.wmw.movieviewer.networking.RemoteApi

open class MovieRepositoryImpl(private val movieDao: MovieDao, private val movieApi: RemoteApi) : MovieRepository {
    override fun getAllMovies(): LiveData<List<Movie>> = movieDao.getAllMoviesSortedByTitle()

    override suspend fun getMovieById(movieId: String?): Movie = movieDao.getMovieById(movieId)

    override suspend fun loadMoviesForPage(startingPage: Int, endingPage: Int) {

        val moviesList = movieApi.getTopMovies(startingPage, endingPage)

        if (moviesList is Success) {
            saveMoviesToDatabase(moviesList.data.data.movies.toList())
        }
    }

    override suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

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
}
