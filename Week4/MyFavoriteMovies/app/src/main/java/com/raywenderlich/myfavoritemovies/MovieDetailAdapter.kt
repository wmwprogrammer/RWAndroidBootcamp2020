package com.raywenderlich.myfavoritemovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieDetailAdapter(private var movie: Movie?) :
    RecyclerView.Adapter<MovieDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_detail_view_holder, parent, false)
        return MovieDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MovieDetailViewHolder, position: Int) {
        holder.moviePosterImageView.setImageResource(movie?.poster!!)
        holder.genreTextView.text = movie?.genre
        holder.releaseDateTextView.text = movie?.releaseDate
        holder.movieSummaryTextView.text = movie?.summary
    }
}