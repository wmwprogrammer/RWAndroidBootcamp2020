package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.worker.SynchronizeMovieDatabaseWorker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault
import java.util.concurrent.TimeUnit

@UnstableDefault
fun startMainActivity(from: Context) = from.startActivity(Intent(from, MainActivity::class.java))

@UnstableDefault
class MainActivity : AppCompatActivity() {


    private val viewModel by lazy {
        ViewModelProvider(this, App.viewModelFactory).get(MovieViewModel::class.java)
    }

    private val movieAdapter by lazy { MovieAdapter(::movieItemClicked, ::movieItemLongClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMovies()
        viewModel.fetchMovies()
        startPeriodicJob()
    }

    private fun startPeriodicJob() {
        val constraints = setConstraints()
        val worker = buildWorker(constraints)

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(worker)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    @UnstableDefault
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutMenuItem) {
            viewModel.logOut()
            navigateToLogin()
        }
        return false
    }

    @UnstableDefault
    private fun initMovies() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = movieAdapter
        moviesRecyclerView.addOnScrollListener(LazyLoadingListener {
            viewModel.fetchMovies()
        })

        viewModel.getMovies().observe(this, Observer {
            if (it != null) {
                movieAdapter.setMovies(it)
            }
        })
    }

    private fun movieItemClicked(movie: Movie) {
        startDetailActivity(this, movie)
    }

    @UnstableDefault
    private fun movieItemLongClicked(movie: Movie): Boolean {
        lifecycleScope.launch {
            viewModel.deleteMovie(movie)
        }
        return true
    }

    private fun navigateToLogin() {
        startLoginActivity(this)
        finish()
    }
}