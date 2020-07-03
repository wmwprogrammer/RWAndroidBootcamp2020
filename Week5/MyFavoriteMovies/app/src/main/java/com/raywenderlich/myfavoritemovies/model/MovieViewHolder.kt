package com.raywenderlich.myfavoritemovies.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_list_view_holder.view.*

class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) = with(containerView) {
        movieTextView.text = movie.title
        movieTextView.setCompoundDrawablesWithIntrinsicBounds(
            movie.poster, 0, 0, 0
        )
        rootView.setOnClickListener { onMovieClick(movie) }
    }
}
