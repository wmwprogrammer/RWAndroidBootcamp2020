package com.raywenderlich.myfavoritemovies.activities

import android.content.Intent
import android.os.Bundle
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter =
            MovieAdapter(
                DummyData.movies,
                this
            )
    }

    override fun movieItemClicked(movie: Movie) {
        val movieItem = Intent(this, DetailActivity::class.java)
        movieItem.putExtra(MOVIE_DETAILS, movie)
        startActivity(movieItem)
    }
}