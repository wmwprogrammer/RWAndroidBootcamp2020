package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.wmw.movieviewer.App
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.worker.SynchronizeMovieDatabaseWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MovieViewModel(
    private val repository: MovieRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var currentPage = 1

    fun getMovies(): LiveData<List<Movie>> = repository.getAllMovies()

    fun fetchMovies() {
        viewModelScope.launch {
            val endingPage = currentPage + 9
            repository.loadMoviesForPage(currentPage, endingPage)
            currentPage++
        }
    }

    fun logOut() {
        userRepository.setUserLoggedIn(false)
    }

    fun setupSync() {
        val constraints = setConstraints()
        val worker = buildWorker(constraints)

        val workManager = WorkManager.getInstance(App.getAppContext())
        workManager.enqueue(worker)
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }

    private fun buildWorker(constraints: Constraints): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<SynchronizeMovieDatabaseWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
    }

    private fun setConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()
    }
}