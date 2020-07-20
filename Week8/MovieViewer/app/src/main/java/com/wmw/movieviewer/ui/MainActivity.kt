package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.worker.SynchronizeMovieDatabaseWorker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun startMainActivity(from: Context) = from.startActivity(Intent(from, MainActivity::class.java))

class MainActivity : AppCompatActivity() {
    private val movieRepository by lazy { MovieRepository(App.movieDb.movieDao(), App.remoteApi) }
    private val userRepository by lazy { UserRepository() }
    private val movieAdapter by lazy { MovieAdapter(::movieItemClicked, ::movieItemLongClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviesRecyclerView.adapter = movieAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            movieRepository.refreshMovies()
        }
        movieRepository.getAllMovies().observe(this, Observer {
            movieAdapter.setMovies(it)
        })

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()


        val work = PeriodicWorkRequestBuilder<SynchronizeMovieDatabaseWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(work)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutMenuItem) {
            userRepository.setUserLoggedIn(false)
            navigateToLogin()
        }
        return false
    }

    private fun movieItemClicked(movie: Movie) {
        startDetailActivity(this, movie.id)
    }

    //I know this function can be split up, but I am not sure how to do it.
    private fun movieItemLongClicked(movie: Movie): Boolean {
        movieAdapter.deleteMovie(movie)
        return true
    }

    private fun navigateToLogin() {
        startLoginActivity(this)
        finish()
    }
}