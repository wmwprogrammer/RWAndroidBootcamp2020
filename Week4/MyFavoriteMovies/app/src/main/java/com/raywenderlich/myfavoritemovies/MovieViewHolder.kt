package com.raywenderlich.myfavoritemovies

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_list_view_holder.view.*

class MovieViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView) {
    var movieTextView: TextView = movieView.movieTextView
}