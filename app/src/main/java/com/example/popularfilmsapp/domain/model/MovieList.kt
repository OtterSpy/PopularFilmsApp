package com.example.popularfilmsapp.domain.model

data class MovieList(
    val page: Int,
    val results: List<MovieItem>
)