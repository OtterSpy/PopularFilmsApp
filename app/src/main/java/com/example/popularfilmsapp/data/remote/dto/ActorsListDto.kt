package com.example.popularfilmsapp.data.remote.dto

import com.example.popularfilmsapp.domain.model.ActorsList
import com.example.popularfilmsapp.domain.model.Cast

data class ActorsListDto(
    val cast: List<Cast>,
    val id: Int
)

fun ActorsListDto.toActorsList() =
    ActorsList(
        cast,
        id
    )