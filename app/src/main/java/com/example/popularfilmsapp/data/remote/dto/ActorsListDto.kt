package com.example.popularfilmsapp.data.remote.dto

import com.example.popularfilmsapp.domain.model.ActorsList
import com.example.popularfilmsapp.domain.model.Cast
import com.example.popularfilmsapp.domain.model.Crew

data class ActorsListDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

fun ActorsListDto.toActorsList() =
    ActorsList(
        cast,
        crew,
        id
    )