package com.wmw.movieviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.validators.CredentialsValidator

class LoginViewModel(
    private val credentialsValidator: CredentialsValidator,
    private val userRepository: UserRepository
) : ViewModel() {

    private val loginViewState = MutableLiveData<LoginViewState>()

    fun getLoginViewState(): LiveData<LoginViewState> = loginViewState

    fun checkIfUserLoggedIn() {
        if (userRepository.isUserLoggedIn()) {
            loginViewState.value = LoginViewState.UserLoggedIn
        }
    }

    fun checkCredentials(username: String, password: String) {
        credentialsValidator.setCredentials(username, password)
        checkUsername()
        checkPassword()
        if (credentialsValidator.areCredentialsValid()) {
            userRepository.setUserLoggedIn(true)
            loginViewState.value = LoginViewState.UserLoggedIn
        }
    }

    private fun checkUsername() {
        if (!credentialsValidator.isUsernameValid()) {
            loginViewState.value = LoginViewState.InvalidUsername
        }
    }

    private fun checkPassword() {
        if (!credentialsValidator.isPasswordValid()) {
            loginViewState.value = LoginViewState.InvalidPassword
        }
    }
}

sealed class LoginViewState {
    object UserLoggedIn : LoginViewState()
    object InvalidUsername : LoginViewState()
    object InvalidPassword : LoginViewState()
}