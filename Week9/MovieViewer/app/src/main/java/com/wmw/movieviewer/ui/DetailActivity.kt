package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault

private const val MOVIE_KEY = "movie_id"

fun startDetailActivity(from: Context, movieId: String?) =
    from.startActivity(Intent(from, DetailActivity::class.java).apply {
        putExtra(MOVIE_KEY, movieId)
    })

class DetailActivity : AppCompatActivity() {

    private val movieId by lazy { intent.getStringExtra(MOVIE_KEY) }

    @UnstableDefault
    private val repository by lazy { App.repository }

    @UnstableDefault
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