package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wmw.movieviewer.repository.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private val moviesRepository: MoviesRepository = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieDetailViewModel = MovieDetailViewModel(moviesRepository)
    }

    @Test
    fun `test getMovieById calls correct call in repository`() = testCoroutineRule.runBlockingTest {
        movieDetailViewModel.getMovieById("1")
        verify(moviesRepository).getMovieById("1")
    }
}