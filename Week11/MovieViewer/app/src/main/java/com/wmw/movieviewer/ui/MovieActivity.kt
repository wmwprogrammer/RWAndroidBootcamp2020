package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_list_view_holder.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import androidx.core.util.Pair

fun startMainActivity(from: Context) = from.startActivity(Intent(from, MovieActivity::class.java))

class MovieActivity : AppCompatActivity() {
    private val viewModel by inject<MovieViewModel>()
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
//        startDetailActivity(this, movie.id)
        val detailsIntent = Intent(this, MovieDetailActivity::class.java)
        detailsIntent.putExtra(getString(R.string.bundle_extra_item), movie.id)
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair.create<View, String>(
                imageView,
                getString(R.string.transition_image)),
            Pair.create<View, String>(
                movieTextView,
                getString(R.string.transition_title)))
        startActivity(detailsIntent, activityOptions.toBundle())
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