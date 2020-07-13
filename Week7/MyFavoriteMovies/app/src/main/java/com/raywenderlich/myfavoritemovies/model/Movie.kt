package com.raywenderlich.myfavoritemovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
class Movie(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var releaseDate: String,
    var title: String,
    var plot: String,
    var genres: List<String>,
    var urlPoster: String
)