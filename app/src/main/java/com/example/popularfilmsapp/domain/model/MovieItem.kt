package com.example.popularfilmsapp.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieItem(
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: String,
    val title: String
) : Serializable
