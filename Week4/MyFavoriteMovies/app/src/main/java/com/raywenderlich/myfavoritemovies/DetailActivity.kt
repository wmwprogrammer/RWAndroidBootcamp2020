package com.raywenderlich.myfavoritemovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie: Movie? = intent.getParcelableExtra(MovieAdapter.MOVIE_KEY)

        movie_detail_recyclerview.layoutManager = LinearLayoutManager(this)
        movie_detail_recyclerview.adapter = MovieDetailAdapter(movie)
    }
}