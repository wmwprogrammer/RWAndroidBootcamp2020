package com.wmw.movieviewer.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_list_view_holder.view.*

class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(
        movie: Movie,
        onMovieClick: (Movie) -> Unit,
        onMovieLongClicked: (Movie) -> Boolean
    ) = with(containerView) {
        movieTextView.text = movie.title
        Glide.with(this).load(movie.urlPoster).into(imageView)

        rootView.setOnClickListener { onMovieClick(movie) }
        rootView.setOnLongClickListener { onMovieLongClicked(movie) }
    }
}
