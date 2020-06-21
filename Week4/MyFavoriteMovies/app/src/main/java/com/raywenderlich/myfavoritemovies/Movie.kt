package com.raywenderlich.myfavoritemovies

import java.time.LocalDate

class Movie(
    var id: Int,
    var releaseDate: LocalDate,
    var title: String,
    var summary: String,
    var genre: String,
    var poster: Int
)