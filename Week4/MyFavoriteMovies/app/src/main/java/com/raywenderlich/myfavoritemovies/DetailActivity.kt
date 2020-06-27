package com.raywenderlich.myfavoritemovies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie: Movie? = intent.getParcelableExtra(MovieAdapter.MOVIE_KEY)

        movieDetailRecyclerView.layoutManager = LinearLayoutManager(this)
        movieDetailRecyclerView.adapter = MovieDetailAdapter(movie)
    }
}