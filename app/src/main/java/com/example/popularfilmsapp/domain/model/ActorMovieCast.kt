package com.example.popularfilmsapp.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ActorMovieCast(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val character: String?,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: String
) : Serializable

fun ActorMovieCast.toMovieItem() =
    MovieItem(
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity.toString(),
        posterPath,
        releaseDate,
        voteAverage,
        title
    )