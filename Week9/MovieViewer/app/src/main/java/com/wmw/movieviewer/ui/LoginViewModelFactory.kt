package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmw.movieviewer.repository.UserRepositoryImpl
import com.wmw.movieviewer.validators.CredentialsValidator

class LoginViewModelFactory(
        private val credentialsValidator: CredentialsValidator,
        private val userRepository: UserRepositoryImpl
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(credentialsValidator, userRepository) as T
    }
}