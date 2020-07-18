package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wmw.movieviewer.R
import com.wmw.movieviewer.networking.NetworkStatusChecker
import com.wmw.movieviewer.onClick
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.validators.CredentialsValidator
import kotlinx.android.synthetic.main.activity_login.*

//this came from the solution
fun startLoginActivity(from: Context) = from.startActivity(Intent(from, LoginActivity::class.java))

class LoginActivity : AppCompatActivity() {
    private val credentialsValidator by lazy { CredentialsValidator() }
    private val userRepository by lazy { UserRepository() }

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(
            getSystemService(
                ConnectivityManager::class.java
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkStatusChecker.performIfConnectedToInternet {
            checkIfUserLoggedIn()
            //used this method from the solution to avoid setting up a click listener every time
            loginButton.onClick { checkCredentials() }
        }
    }

    private fun checkIfUserLoggedIn() {
        if (userRepository.isUserLoggedIn()) {
            startMainActivity()
        }
    }

    private fun checkCredentials() {
        credentialsValidator.setCredentials(
            usernameEditText.text.toString(),
            passwordEditText.text.toString()
        )

        toggleUsernameState()
        togglePasswordState()

        if (credentialsValidator.areCredentialsValid()) {
            userRepository.setUserLoggedIn(true)
            startMainActivity()
        }
    }

    private fun toggleUsernameState() {
        if (!credentialsValidator.isUsernameValid()) {
            showErrorToast("Username cannot be less than 8 characters")
        }
    }

    private fun togglePasswordState() {
        if (!credentialsValidator.isPasswordValid()) {
            showErrorToast("Password must be greater than 8 characters")
        }
    }

    private fun showErrorToast(toastText: String) {
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
    }

    private fun startMainActivity() {
        startMainActivity(this)
        finish()        //stop the login activity, so that it does not show when hitting back
    }
}