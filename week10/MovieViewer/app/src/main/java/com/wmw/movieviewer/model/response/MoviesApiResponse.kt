package com.wmw.movieviewer.model.response

data class MovieTopLevelResponse(val data: MoviesResultResponse, val about: AboutResultResponse)

data class AboutResultResponse(val version: String, val serverTime: String)

data class MoviesResultResponse(val movies: List<MovieResponse>)

data class MovieResponse(
    var idIMDB: String,
    var releaseDate: String,
    var title: String,
    var plot: String,
    var genres: List<String>,
    var urlPoster: String,
    var ranking: Int
)