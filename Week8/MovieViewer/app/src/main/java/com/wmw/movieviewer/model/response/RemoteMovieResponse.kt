package com.wmw.movieviewer.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieTopLevelResponse(val data: MoviesResultResponse, val about: AboutResultResponse)

@Serializable
data class AboutResultResponse(val version: String, val serverTime: String)

@Serializable
data class MoviesResultResponse(val movies: Array<MovieResponse>)

@Serializable
data class MovieResponse(
    var idIMDB: String,
    var releaseDate: String,
    var title: String,
    var plot: String,
    var genres: List<String>,
    var urlPoster: String
)