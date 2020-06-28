package com.raywenderlich.myfavoritemovies.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_list_view_holder.view.*

class MovieViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView) {
    var movieTextView: TextView = movieView.movieTextView

    fun bind(movie: Movie) {
        movieTextView.text = movie.title
        movieTextView.setCompoundDrawablesWithIntrinsicBounds(
            movie.poster, 0, 0, 0
        )
    }
}
