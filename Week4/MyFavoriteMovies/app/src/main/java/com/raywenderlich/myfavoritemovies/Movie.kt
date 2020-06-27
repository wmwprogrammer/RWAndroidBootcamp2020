package com.raywenderlich.myfavoritemovies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    var id: Int,
    var releaseDate: String,
    var title: String,
    var summary: String,
    var genre: String,
    var poster: Int
) : Parcelable