package com.wmw.movieviewer.data.networking

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromGenre(value: List<String>): String {
        return value.joinToString()
    }

    @TypeConverter
    fun toGenre(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }
}