package com.example.popularfilmsapp.data.remote.dto

import com.example.popularfilmsapp.domain.model.ActorMovieCast
import com.google.gson.annotations.SerializedName

data class ActorMovieCastDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    val order: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun ActorMovieCastDto.toActorMovieCast() =
    ActorMovieCast(
        backdropPath,
        id,
        originalLanguage,
        originalTitle,
        character,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        voteAverage.toString()
    )