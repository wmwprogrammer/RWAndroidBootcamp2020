package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import com.wmw.movieviewer.repository.UserRepositoryImpl
import com.wmw.movieviewer.validators.CredentialsValidator

class LoginViewModel(
        private val credentialsValidator: CredentialsValidator,
        private val userRepository: UserRepositoryImpl
) : ViewModel() {

    fun checkIfUserLoggedIn(): Boolean {
        return userRepository.isUserLoggedIn()
    }

    fun setUserLoggedIn(value: Boolean) {
        userRepository.setUserLoggedIn(value)
    }


}