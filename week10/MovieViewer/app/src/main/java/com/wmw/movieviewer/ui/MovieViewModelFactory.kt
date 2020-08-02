package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmw.movieviewer.repository.MoviesRepository
import com.wmw.movieviewer.repository.UserRepository

class MovieViewModelFactory(
    private val moviesRepository: MoviesRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(moviesRepository, userRepository) as T
    }
}