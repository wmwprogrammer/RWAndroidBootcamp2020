package com.raywenderlich.myfavoritemovies.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.myfavoritemovies.R
import com.raywenderlich.myfavoritemovies.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie: Movie? = intent.getParcelableExtra(MainActivity.MOVIE_DETAILS)
        moviePosterImageView.setImageResource(movie?.poster!!)
        genreTextView.text = movie.genre
        releaseDateTextView.text = movie.releaseDate
        movieSummaryTextView.text = movie.summary
    }
}