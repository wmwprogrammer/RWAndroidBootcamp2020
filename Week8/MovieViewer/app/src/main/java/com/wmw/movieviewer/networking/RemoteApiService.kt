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

    @GET("/imdb/top")
    suspend fun getTopMovies(
        @Query("token") token: String,
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("data") data: String,
        @Query("format") format: String
    ): MovieTopLevelResponse
}