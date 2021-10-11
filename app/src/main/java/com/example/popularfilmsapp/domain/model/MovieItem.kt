package com.example.popularfilmsapp.domain.model

data class MovieItem(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val title: String
)
