package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//private const val MOVIE_KEY = "movieId"
//
//fun startDetailActivity(from: Context, movieId: String) =
//    from.startActivity(Intent(from, MovieDetailActivity::class.java).apply {
//        putExtra(MOVIE_KEY, movieId)
//    })

class MovieDetailActivity : AppCompatActivity() {
    private val movieId by lazy { intent.getStringExtra(getString(R.string.bundle_extra_item)) }
    private val viewModel by inject<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        observeChanges()
        viewModel.getMovieById(movieId)
    }

    private fun observeChanges() {
        viewModel.getDetailMovieLiveData().observe(this, Observer {
            displayMovieDetails(it)
        })
    }

    private fun displayMovieDetails(movie: Movie) {
        val date = LocalDate.parse(movie.releaseDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
        val formattedDate = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))

        genreTextView.text = movie.genres[0]
        releaseDateTextView.text = formattedDate
        movieSummaryTextView.text = movie.plot
        Glide.with(this).load(movie.urlPoster).into(moviePosterImageView)
    }
}