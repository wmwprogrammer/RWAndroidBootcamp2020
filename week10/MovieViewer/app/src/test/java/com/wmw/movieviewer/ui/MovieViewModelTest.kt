package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    private val movieRepository: MovieRepository = mock()
    private val userRepository: UserRepository = mock()
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieViewModel = MovieViewModel(movieRepository, userRepository)
    }

    @Test
    fun logOut() {
        movieViewModel.logOut()
        verify(userRepository).setUserLoggedIn(false)
    }

    @Test
    fun getMovies() {
        testCoroutineRule.runBlockingTest {
            movieViewModel.fetchMovies()
            verify(movieRepository).loadMoviesForPage(1, 10)
        }
    }
}