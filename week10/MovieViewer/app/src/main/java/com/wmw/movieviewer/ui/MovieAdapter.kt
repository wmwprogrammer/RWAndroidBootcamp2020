package com.wmw.movieviewer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wmw.movieviewer.R
import com.wmw.movieviewer.model.Movie
import org.koin.core.KoinComponent

class MovieAdapter(
    private val onMovieClicked: (Movie) -> Unit,
    private val onMovieLongClicked: (Movie) -> Boolean
) : RecyclerView.Adapter<MovieViewHolder>(), KoinComponent {

    private var movies = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_view_holder, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onMovieClicked, onMovieLongClicked)
    }
}