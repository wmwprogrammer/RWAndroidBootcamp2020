package com.wmw.movieviewer.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wmw.movieviewer.App
import com.wmw.movieviewer.repository.MoviesRepository
import com.wmw.movieviewer.repository.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.UnstableDefault
import org.koin.core.KoinComponent
import org.koin.core.inject

class SynchronizeMovieDatabaseWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params), KoinComponent {

    private val moviesRepository by inject<MoviesRepositoryImpl>()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.Main) {
            Toast.makeText(App.getAppContext(), "Running Background Sync", Toast.LENGTH_LONG).show()
        }
        moviesRepository.loadMoviesForPage()
        return Result.success()
    }
}