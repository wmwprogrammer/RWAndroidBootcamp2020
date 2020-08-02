package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository

class MovieViewModelFactory(
        private val repository: MovieRepository,
        private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository, userRepository) as T
    }
}