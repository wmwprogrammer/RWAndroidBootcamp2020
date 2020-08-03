package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmw.movieviewer.repository.MoviesRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailViewModelFactory: ViewModelProvider.Factory, KoinComponent {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel() as T
    }
}