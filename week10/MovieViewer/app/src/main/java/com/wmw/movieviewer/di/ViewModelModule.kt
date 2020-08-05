package com.wmw.movieviewer.di

import com.wmw.movieviewer.ui.LoginViewModel
import com.wmw.movieviewer.ui.MovieDetailViewModel
import com.wmw.movieviewer.ui.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MovieViewModel(get(), get())
    }
    viewModel {
        MovieDetailViewModel(get())
    }
    viewModel {
        LoginViewModel(get(), get())
    }
}