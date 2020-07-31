package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmw.movieviewer.repository.MovieRepositoryImpl
import com.wmw.movieviewer.repository.UserRepositoryImpl

class MovieViewModelFactory(
        private val repository: MovieRepositoryImpl,
        private val userRepository: UserRepositoryImpl
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository, userRepository) as T
    }
}