package com.wmw.movieviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wmw.movieviewer.validators.CredentialsValidator
import org.junit.Before

import org.junit.Rule
import com.nhaarman.mockitokotlin2.mock
import com.wmw.movieviewer.repository.UserRepository
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    
    private val credentialsValidator: CredentialsValidator = mock()
    private val userRepository: UserRepository = mock()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(credentialsValidator, userRepository)
    }

    @Test
    fun `viewState is set correctly when user is logged in`() {
        `when`(userRepository.isUserLoggedIn()).thenReturn(true)
        loginViewModel.checkIfUserLoggedIn()
        assertThat(LoginViewState.UserLoggedIn, equalTo(loginViewModel.getLoginViewState().value))
    }

    @Test
    fun `InvalidPassword is triggered if password is invalid`() {
        credentialsValidator.setCredentials("","")
        loginViewModel.checkPassword()
        assertThat(LoginViewState.InvalidPassword, equalTo(loginViewModel.getLoginViewState().value))
    }

    @Test
    fun `InvalidUsername is triggered if username is invalid`() {
        credentialsValidator.setCredentials("","")
        loginViewModel.checkUsername()
        assertThat(LoginViewState.InvalidUsername, equalTo(loginViewModel.getLoginViewState().value))
    }
}