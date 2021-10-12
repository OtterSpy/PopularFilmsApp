package com.example.popularfilmsapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val title: String
)
