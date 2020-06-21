package com.raywenderlich.myfavoritemovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movies_recyclerview.layoutManager = LinearLayoutManager(this)
        movies_recyclerview.adapter = MovieAdapter()
    }
}