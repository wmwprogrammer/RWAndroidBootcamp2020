package com.wmw.movieviewer.validators

interface CredentialsValidator {
    fun setCredentials(username: String, password: String)
    fun isUsernameValid(): Boolean
    fun isPasswordValid(): Boolean
    fun areCredentialsValid(): Boolean
}