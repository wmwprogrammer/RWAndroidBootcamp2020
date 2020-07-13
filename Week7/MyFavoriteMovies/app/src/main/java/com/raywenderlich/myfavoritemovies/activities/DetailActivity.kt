package com.raywenderlich.myfavoritemovies.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.raywenderlich.myfavoritemovies.R
import com.raywenderlich.myfavoritemovies.model.Movie
import com.raywenderlich.myfavoritemovies.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch

private const val MOVIE_KEY = "movie_id"

//this came from the solution
fun startDetailActivity(from: Context, movieId: Int?) =
    from.startActivity(Intent(from, DetailActivity::class.java).apply {
        putExtra(MOVIE_KEY, movieId)
    })

class DetailActivity : AppCompatActivity() {

    private val movieId by lazy { intent.getIntExtra(MOVIE_KEY, -1) }
    private val repository by lazy { MovieRepository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        lifecycleScope.launch {
            val movie = repository.getMovieById(movieId)
            displayMovieDetails(movie)
        }
    }

    private fun displayMovieDetails(movie: Movie) {
        Glide.with(this).load(movie.urlPoster).into(moviePosterImageView)
        genreTextView.text = movie.genres[0]
        releaseDateTextView.text = movie.releaseDate
        movieSummaryTextView.text = movie.plot
    }
}