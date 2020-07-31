package com.wmw.movieviewer.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wmw.movieviewer.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault

class SynchronizeMovieDatabaseWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    @UnstableDefault
    private val movieRepository by lazy { App.movieRepository }

    @UnstableDefault
    override suspend fun doWork(): Result {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(App.getAppContext(), "Running Background Sync", Toast.LENGTH_LONG).show()
        }
        movieRepository.loadMoviesForPage()
        return Result.success()
    }
}