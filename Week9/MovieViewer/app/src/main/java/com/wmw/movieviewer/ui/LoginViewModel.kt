package com.wmw.movieviewer.ui

import androidx.lifecycle.ViewModel
import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.validators.CredentialsValidator
import kotlinx.android.synthetic.main.activity_login.*

class LoginViewModel(
        private val credentialsValidator: CredentialsValidator,
        private val userRepository: UserRepository
) : ViewModel() {

    fun checkIfUserLoggedIn(): Boolean {
        return userRepository.isUserLoggedIn()
    }

    fun setUserLoggedIn(value: Boolean) {
        userRepository.setUserLoggedIn(value)
    }


}