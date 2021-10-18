package com.example.popularfilmsapp.domain.model

import java.io.Serializable

data class ActorsList(
    val cast: List<Cast>,
    val id: Int
) : Serializable
