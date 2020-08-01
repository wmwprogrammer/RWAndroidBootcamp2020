package com.wmw.movieviewer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.wmw.movieviewer.App
import com.wmw.movieviewer.R
import com.wmw.movieviewer.onClick
import kotlinx.android.synthetic.main.activity_login.*

fun startLoginActivity(from: Context) = from.startActivity(Intent(from, LoginActivity::class.java))

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels { App.loginViewModelFactory}
//    private val viewModel by lazy {
//        ViewModelProvider(this, App.loginViewModelFactory).get(LoginViewModel::class.java)
//    }
//    private val credentialsValidator by lazy { App.credentialsValidator }

//    private val networkStatusChecker by lazy {
//        NetworkStatusChecker(
//            getSystemService(
//                ConnectivityManager::class.java
//            )
//        )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setClickListeners()
        observeChanges()
        viewModel.checkIfUserLoggedIn()

//        networkStatusChecker.performIfConnectedToInternet {
//           if (viewModel.checkIfUserLoggedIn()) startMainActivity()
//
//            //used this method from the solution to avoid setting up a click listener every time
//            loginButton.onClick { checkCredentials() }
//        }
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

//    private fun subscribeToData() {
//        viewModel.getLoginViewState().subscribe(this, ::onLoginViewStateChanged)
//    }

    private fun onLoginViewStateChanged(loginViewState: LoginViewState) = when (loginViewState) {
        LoginViewState.UserLoggedIn -> startMainActivity()
        LoginViewState.InvalidUsername -> showErrorToast("Username must be more than 8 characters")
        LoginViewState.InvalidPassword -> showErrorToast("Password must be greater than 8 characters")
//        LoginViewState.ValidUsername -> removeUsernameError()
//        LoginViewState.ValidPassword -> removePasswordError()
    }

    private fun startMainActivity() {
        startMainActivity(this)
        finish()
    }

//    private fun toggleUsernameState() {
//        if (!credentialsValidator.isUsernameValid()) {
//            showErrorToast("Username cannot be less than 8 characters")
//        }
//    }
//
//    private fun togglePasswordState() {
//        if (!credentialsValidator.isPasswordValid()) {
//            showErrorToast("Password must be greater than 8 characters")
//        }
//    }

    private fun showErrorToast(toastText: String) {
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
    }

//    private fun startMainActivity() {
//        startMainActivity(this)
//        finish()        //stop the login activity, so that it does not show when hitting back
//    }
}