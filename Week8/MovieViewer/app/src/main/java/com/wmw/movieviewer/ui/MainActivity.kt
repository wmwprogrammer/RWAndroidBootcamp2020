package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import kotlinx.coroutines.launch

//this came from the solution
fun startMainActivity(from: Context) = from.startActivity(Intent(from, MainActivity::class.java))

class MainActivity : AppCompatActivity() {
    private val movieRepository by lazy { MovieRepository(App.movieDb.movieDao(), App.remoteApi) }
    private val userRepository by lazy { UserRepository() }
    private val movieAdapter by lazy { MovieAdapter(::movieItemClicked, ::movieItemLongClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            movieRepository.refreshMovies()
        }
//        fillMovieList()
    }

//    private fun fillMovieList() {
//        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
//        moviesRecyclerView.adapter = movieAdapter
//        lifecycleScope.launch {
//            val movies = movieRepository.getAllMovies()
//            updateMovieListing(movies, this)
//        }
//    }

//    private fun updateMovieListing(movies: LiveData<List<Movie>>, coroutineScope: CoroutineScope) {
//        coroutineScope.launch {
//            movieAdapter.setMovies(movies)
//        }
//    }

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
        lifecycleScope.launch {
            movieRepository.deleteMovieById(movie.id)
            val movies = movieRepository.getAllMovies()
//            updateMovieListing(movies, this)
        }
        return true
    }

    private fun navigateToLogin() {
        startLoginActivity(this)
        finish()
    }
}