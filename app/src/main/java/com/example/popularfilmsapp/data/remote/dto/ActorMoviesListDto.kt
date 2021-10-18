package com.example.popularfilmsapp.data.remote.dto

import com.example.popularfilmsapp.domain.model.ActorMovieCast
import com.example.popularfilmsapp.domain.model.ActorMoviesList

data class ActorMoviesListDto(
    val cast: List<ActorMovieCast>,
    val id: Int
)

fun ActorMoviesListDto.toActorMoviesList() =
    ActorMoviesList(
        cast,
        id
    )