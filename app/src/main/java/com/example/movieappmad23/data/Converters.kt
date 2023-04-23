package com.example.movieappmad23.data

import androidx.room.TypeConverter
import com.example.movieappmad23.models.Genre




class Converters {
    @TypeConverter
    fun genreToString(genres: List<Genre>): String {
        return genres.joinToString(separator = ";")
    }

    @TypeConverter
    fun imagesToString(images: List<String>): String{
        return images.joinToString(separator = ";")
    }

    @TypeConverter
    fun genreStringToList(genres: String): List<Genre> {
        return genres.split(";").map{Genre.valueOf(it)}
    }

    @TypeConverter
    fun imagesStringToList(images: String): List<String> {
        return images.split(";")
    }
}