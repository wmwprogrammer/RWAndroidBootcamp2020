package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.MovieRepositoryImpl
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.repository.UserRepositoryImpl
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockMovieRepository: MovieRepositoryImpl

    @Mock
    lateinit var mockUserRepository: UserRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieViewModel = MovieViewModel(mockMovieRepository, mockUserRepository)
    }

    @Test
    fun logOut() {
        movieViewModel.logOut()
        assertFalse(mockUserRepository.isUserLoggedIn())
    }
}