package com.wmw.movieviewer.data.networking

import com.wmw.movieviewer.App
import com.wmw.movieviewer.model.Failure
import com.wmw.movieviewer.model.Result
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.model.response.MovieTopLevelResponse


const val BASE_URL = "https://www.myapifilms.com"

class RemoteApi(private val apiService: RemoteApiService) {

    suspend fun getMovie(movieName: String): Result<MovieTopLevelResponse> = try {
        val response = apiService.getMovie(App.getToken(), movieName)

        Success(response)
    } catch (error: Throwable) {
        Failure(error)
    }
}