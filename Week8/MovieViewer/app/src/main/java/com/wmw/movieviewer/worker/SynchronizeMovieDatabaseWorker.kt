package com.wmw.movieviewer.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wmw.movieviewer.App
import com.wmw.movieviewer.repository.MovieRepository

class SynchronizeMovieDatabaseWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val movieRepository by lazy { MovieRepository(App.movieDb.movieDao(), App.remoteApi) }

    override suspend fun doWork(): Result {
        movieRepository.refreshMovies()
        return Result.success()
    }

}