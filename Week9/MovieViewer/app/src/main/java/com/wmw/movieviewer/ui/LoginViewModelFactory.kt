package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.validators.CredentialsValidator

class LoginViewModelFactory(
        private val credentialsValidator: CredentialsValidator,
        private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(credentialsValidator, userRepository) as T
    }
}