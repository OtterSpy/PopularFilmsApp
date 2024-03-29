package com.example.popularfilmsapp.data.remote.dto

import com.example.popularfilmsapp.domain.model.Cast
import com.google.gson.annotations.SerializedName

data class CastDto(
    val adult: Boolean,
    @SerializedName("cast_id")
    val castId: Int,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)

fun CastDto.toCast() =
    Cast(
        character,
        name,
        id,
        profilePath,
        creditId
    )