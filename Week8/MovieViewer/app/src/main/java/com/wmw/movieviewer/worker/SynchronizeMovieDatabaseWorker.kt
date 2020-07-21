package com.wmw.movieviewer.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wmw.movieviewer.App
import com.wmw.movieviewer.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SynchronizeMovieDatabaseWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val movieRepository by lazy { MovieRepository(App.movieDb.movieDao(), App.remoteApi) }

    override suspend fun doWork(): Result {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(App.getAppContext(), "Running Background Sync", Toast.LENGTH_LONG).show()
        }
        movieRepository.refreshMovies()
        return Result.success()
    }
}