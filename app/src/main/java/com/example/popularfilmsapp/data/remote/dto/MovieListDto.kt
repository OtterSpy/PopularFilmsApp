package com.example.popularfilmsapp.data.remote.dto

import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.domain.model.MovieList
import com.google.gson.annotations.SerializedName

data class MovieListDto(
    val page: Int,
    val results: List<MovieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MovieListDto.toMovieList() =
    MovieList(
        page,
        results
    )