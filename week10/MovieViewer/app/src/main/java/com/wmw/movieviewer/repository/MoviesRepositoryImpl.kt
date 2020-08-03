package com.wmw.movieviewer.repository

import androidx.lifecycle.LiveData
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.model.MovieDao
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.model.response.MovieResponse
import com.wmw.movieviewer.networking.MoviesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

open class MoviesRepositoryImpl() : MoviesRepository, KoinComponent {
    private val moviesApi by inject<MoviesApi>()
    private val movieDao by inject<MovieDao>()
    override fun getAllMovies(): LiveData<List<Movie>> = movieDao.getAllMoviesSortedByTitle()

    override suspend fun getMovieById(movieId: String?): Movie = movieDao.getMovieById(movieId)

    override suspend fun loadMoviesForPage(startingPage: Int, endingPage: Int) {

        val moviesList = moviesApi.getTopMovies(startingPage, endingPage)

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
