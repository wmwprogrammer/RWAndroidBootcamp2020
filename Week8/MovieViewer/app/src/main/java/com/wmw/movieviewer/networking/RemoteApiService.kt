package com.wmw.movieviewer.networking

import com.wmw.movieviewer.model.response.MovieTopLevelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("/imdb/idIMDB")
    suspend fun getMovie(
        @Query("token") token: String,
        @Query("title") title: String
    ): MovieTopLevelResponse

    @GET("/imdb/idIMDB")
    suspend fun getMovies(
        @Query("token") token: String,
        @Query("title") title: String,
        @Query("limit") limit: Int
    )
}