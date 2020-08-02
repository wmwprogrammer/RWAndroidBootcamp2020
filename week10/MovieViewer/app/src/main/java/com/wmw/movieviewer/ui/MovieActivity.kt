package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

fun startMainActivity(from: Context) = from.startActivity(Intent(from, MainActivity::class.java))

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieViewModel> { App.movieViewModelFactory }
    private val movieAdapter by lazy { MovieAdapter(::movieItemClicked, ::movieItemLongClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMovies()
        viewModel.fetchMovies()
        viewModel.setupSync()
        observeChanges()
    }

    private fun observeChanges() {
        viewModel.getMovies().observe(this, Observer {
            if (it != null) {
                movieAdapter.setMovies(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutMenuItem) {
            viewModel.logOut()
            navigateToLogin()
        }
        return false
    }

    private fun initMovies() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = movieAdapter
        moviesRecyclerView.addOnScrollListener(LazyLoadingListener {
            viewModel.fetchMovies()
        })
        viewModel.getMovies()
    }

    private fun movieItemClicked(movie: Movie) {
        startDetailActivity(this, movie.id)
    }

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