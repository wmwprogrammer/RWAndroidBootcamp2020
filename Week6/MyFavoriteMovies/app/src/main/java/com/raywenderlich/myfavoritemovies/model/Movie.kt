package com.raywenderlich.myfavoritemovies.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies_table")
class Movie(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var releaseDate: String,
    var title: String,
    var summary: String,
    var genre: String,
    var poster: Int
) : Parcelable