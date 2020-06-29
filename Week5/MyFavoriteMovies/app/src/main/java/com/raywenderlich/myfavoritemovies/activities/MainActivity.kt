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
import com.raywenderlich.myfavoritemovies.data.DummyData
import com.raywenderlich.myfavoritemovies.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieAdapter.MovieDetailListener {
    companion object {
        const val MOVIE_DETAILS = "movie_details"
        const val IS_LOGGED_IN = "isLoggedIn"
        const val LOGGED_IN_STATUS_KEY = "loggedInStatus"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs =
            getSharedPreferences(LOGGED_IN_STATUS_KEY, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPrefs.getBoolean(IS_LOGGED_IN, false)

        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            moviesRecyclerView.layoutManager = LinearLayoutManager(this)
            moviesRecyclerView.adapter = MovieAdapter(DummyData.movies, this)
        }
    }

    override fun movieItemClicked(movie: Movie) {
        val movieItem = Intent(this, DetailActivity::class.java)
        movieItem.putExtra(MOVIE_DETAILS, movie)
        startActivity(movieItem)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPrefs = getSharedPreferences(LOGGED_IN_STATUS_KEY, Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean(IS_LOGGED_IN, false).apply()
        logout()
        return true
    }

    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}