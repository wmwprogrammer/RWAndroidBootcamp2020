package com.wmw.movieviewer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
class Movie(
    @PrimaryKey var id: String,
    var releaseDate: String,
    var title: String,
    var plot: String,
    var genres: List<String>,
    var urlPoster: String
)