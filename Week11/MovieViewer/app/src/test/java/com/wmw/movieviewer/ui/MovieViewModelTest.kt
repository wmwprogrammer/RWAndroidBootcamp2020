package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wmw.movieviewer.repository.MoviesRepository
import com.wmw.movieviewer.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieViewModelTest : KoinTest {
    private val moviesRepository: MoviesRepository = mock()
    private val userRepository: UserRepository = mock()
    private val mockModule = module {
        single {
            moviesRepository
        }
        single {
            userRepository
        }
    }

    private lateinit var movieViewModel: MovieViewModel

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
        movieViewModel = MovieViewModel()
    }

    @After
    fun tearDown() {
        stopKoin()
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
            verify(moviesRepository).loadMoviesForPage(1, 10)
        }
    }
}