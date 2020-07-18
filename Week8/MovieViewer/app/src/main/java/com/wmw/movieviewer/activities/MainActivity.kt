package com.wmw.movieviewer.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.adapters.MovieAdapter
import com.wmw.movieviewer.model.Movie
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//this came from the solution
fun startMainActivity(from: Context) = from.startActivity(Intent(from, MainActivity::class.java))

class MainActivity : AppCompatActivity() {

    private val movieRepository by lazy { MovieRepository() }
    private val userRepository by lazy { UserRepository() }
    private val movieAdapter by lazy { MovieAdapter(::movieItemClicked, ::movieItemLongClicked) }
    private val remoteApi = App.remoteApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMovieFromApi("Star Wars: Episode I")
        getMovieFromApi("Star Wars: Episode II")
        getMovieFromApi("Star Wars: Episode III")
        getMovieFromApi("Star Wars: Episode IV")
        getMovieFromApi("Star Wars: Episode V")
        getMovieFromApi("Star Wars: Episode VI")
        getMovieFromApi("Star Wars: Episode VII")
        getMovieFromApi("Star Wars: Episode VIII")
        getMovieFromApi("Star Wars: Episode IX")
        fillMovieList()

    }

    private fun getMovieFromApi(movieName: String) {
        lifecycleScope.launch {
            val result = remoteApi.getMovie(movieName)
            if (result is Success) {
                movieRepository.insertMovie(
                    Movie(
                        result.data.data.movies[0].idIMDB,
                        result.data.data.movies[0].releaseDate,
                        result.data.data.movies[0].title,
                        result.data.data.movies[0].plot,
                        result.data.data.movies[0].genres,
                        result.data.data.movies[0].urlPoster
                    )
                )
            }
        }
    }

    private fun fillMovieList() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = movieAdapter
        lifecycleScope.launch {
            val movies = movieRepository.getAllMovies()
            updateMovieListing(movies, this)
        }
    }

    private fun updateMovieListing(movies: List<Movie>, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            movieAdapter.setMovies(movies)
        }
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
        lifecycleScope.launch {
            movieRepository.deleteMovieById(movie.id)
            val movies = movieRepository.getAllMovies()
            updateMovieListing(movies, this)
        }
        return true
    }

    private fun navigateToLogin() {
        startLoginActivity(this)
        finish()
    }
}