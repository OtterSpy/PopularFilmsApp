package com.example.popularfilmsapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val title: String
)