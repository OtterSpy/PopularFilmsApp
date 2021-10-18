package com.example.popularfilmsapp.domain.model

import java.io.Serializable

data class MovieList(
    val page: Int,
    val results: List<MovieItem>
) : Serializable