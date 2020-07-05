package com.raywenderlich.myfavoritemovies.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.myfavoritemovies.R
import com.raywenderlich.myfavoritemovies.model.Movie
import com.raywenderlich.myfavoritemovies.model.MovieRepository
import kotlinx.android.synthetic.main.activity_detail.*

private const val MOVIE_KEY = "movie_id"

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

        val movie = repository.getMovieById(movieId)
        displayMovieDetails(movie)
    }

    private fun displayMovieDetails(movie: Movie) {
        moviePosterImageView.setImageResource(movie.poster)
        genreTextView.text = movie.genre
        releaseDateTextView.text = movie.releaseDate
        movieSummaryTextView.text = movie.summary
    }
}