package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wmw.movieviewer.R
import com.wmw.movieviewer.networking.NetworkStatusChecker
import com.wmw.movieviewer.onClick
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

fun startLoginActivity(from: Context) = from.startActivity(Intent(from, LoginActivity::class.java))

class LoginActivity : AppCompatActivity() {
    private val viewModel by inject<LoginViewModel>()
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setClickListeners()
        observeChanges()
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.checkIfUserLoggedIn()
        }
    }

    private fun observeChanges() {
        viewModel.getLoginViewState().observe(this, Observer {
            onLoginViewStateChanged(it)
        })
    }

    private fun setClickListeners() {
        loginButton.onClick {
            viewModel.checkCredentials(usernameInput.text.toString(), passwordInput.text.toString())
        }
    }

    private fun onLoginViewStateChanged(loginViewState: LoginViewState) = when (loginViewState) {
        LoginViewState.UserLoggedIn -> startMainActivity()
        LoginViewState.InvalidUsername -> showErrorToast("Username must be more than 8 characters")
        LoginViewState.InvalidPassword -> showErrorToast("Password must be greater than 8 characters")
    }

    private fun startMainActivity() {
        startMainActivity(this)
        finish()
    }

    private fun showErrorToast(toastText: String) {
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
    }
}