package com.raywenderlich.myfavoritemovies.model

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class RoomRepository : MovieRepository {
    private val dao: MovieDao = MovieApplication.database.movieDao()
    private val allMovies: LiveData<List<Movie>>

    init {
        allMovies = dao.getAllMovies()
    }

    override fun addMovie(movie: Movie) {
        InsertAsyncTask(dao).execute(movie)
    }

    override fun getMovies() = allMovies

    override fun deleteMovies(movie: Movie) {
        DeleteAsyncTask(dao).execute(movie)
    }

    override fun deleteAllMovies() {
        val arrayMovies = allMovies.value?.toTypedArray()
        if (arrayMovies != null) {
            DeleteAsyncTask(dao).execute(*arrayMovies)
        }
    }

    private class InsertAsyncTask internal constructor(private val dao: MovieDao) :
        AsyncTask<Movie, Void, Void>() {
        override fun doInBackground(vararg params: Movie): Void? {
            dao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: MovieDao) :
        AsyncTask<Movie, Void, Void>() {
        override fun doInBackground(vararg params: Movie): Void? {
            dao.deleteMovies(*params)
            return null
        }

    }
}