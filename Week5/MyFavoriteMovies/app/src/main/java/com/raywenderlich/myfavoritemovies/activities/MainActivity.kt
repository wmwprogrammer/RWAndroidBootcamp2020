package com.raywenderlich.myfavoritemovies.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.myfavoritemovies.R
import com.raywenderlich.myfavoritemovies.adapters.MovieAdapter
import com.raywenderlich.myfavoritemovies.data.DummyData.movies
import com.raywenderlich.myfavoritemovies.model.Movie
import com.raywenderlich.myfavoritemovies.model.MovieRepository
import com.raywenderlich.myfavoritemovies.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*

fun startMainActivity(from: Context) = from.startActivity(Intent(from, MainActivity::class.java))

class MainActivity : AppCompatActivity() {

    private val movieRepository by lazy { MovieRepository() }
    private val userRepository by lazy { UserRepository() }
    private val movieAdapter by lazy { MovieAdapter(::movieItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRepository.storeMoviesIfNotEmpty(movies)
        fillMovieList()
    }

    private fun fillMovieList() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = movieAdapter
        movieAdapter.setMovies(movieRepository.getAllMovies())
    }

    private fun movieItemClicked(movie: Movie) {
        startDetailActivity(this, movie.id)
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

    private fun navigateToLogin() {
        startLoginActivity(this)
        finish()
    }
}