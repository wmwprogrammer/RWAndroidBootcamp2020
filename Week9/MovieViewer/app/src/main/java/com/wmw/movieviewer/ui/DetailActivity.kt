package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.serialization.UnstableDefault

private const val MOVIE_KEY = "movie"

@UnstableDefault
fun startDetailActivity(from: Context, movie: Movie?) =
    from.startActivity(Intent(from, DetailActivity::class.java).apply {
        putExtra(MOVIE_KEY, movie)
    })

@UnstableDefault
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(this, App.detailViewModelFactory).get(MovieDetailViewModel::class.java)
    }

    private val liveMovie: LiveData<Movie> by lazy {
        detailViewModel.getDetailMovieLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        liveMovie.observe(this, Observer {
            genreTextView.text = it.genres[0]
            releaseDateTextView.text = it.releaseDate
            movieSummaryTextView.text = it.plot
            Glide.with(this).load(it.urlPoster).into(moviePosterImageView)
        })

        val movie: Movie? = intent.getParcelableExtra(MOVIE_KEY)
        detailViewModel.setDetailMovie(movie)
    }

}