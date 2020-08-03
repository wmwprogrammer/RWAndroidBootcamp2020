package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wmw.movieviewer.repository.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest : KoinTest {
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private val moviesRepository: MoviesRepository = mock()
    private val mockModule = module {
        single {
            moviesRepository
        }
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(listOf(mockModule))
        }
        movieDetailViewModel = MovieDetailViewModel()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test getMovieById calls correct call in repository`() = testCoroutineRule.runBlockingTest {
        movieDetailViewModel.getMovieById("1")
        verify(moviesRepository).getMovieById("1")
    }
}