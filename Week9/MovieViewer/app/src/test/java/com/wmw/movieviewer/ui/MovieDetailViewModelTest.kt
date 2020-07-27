package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class MovieDetailViewModelTest {
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieDetailViewModel = MovieDetailViewModel()
    }
}