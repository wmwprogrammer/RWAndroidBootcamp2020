package com.raywenderlich.myfavoritemovies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_detail_view_holder.view.*

class MovieDetailViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView) {
    val moviePosterImageView: ImageView = movieView.moviePosterImageView
    val genreTextView: TextView = movieView.genreTextView
    val releaseDateTextView: TextView = movieView.releaseDateTextView
    val movieSummaryTextView: TextView = movieView.movieSummaryTextView
}