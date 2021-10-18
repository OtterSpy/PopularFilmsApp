package com.example.popularfilmsapp.domain.model

import java.io.Serializable

data class ActorMoviesList(
    val cast: List<ActorMovieCast>,
    val id: Int
) : Serializable
