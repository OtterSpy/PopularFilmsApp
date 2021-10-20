package com.example.popularfilmsapp.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ActorDetails(
    val biography: String?,
    val birthday: String?,
    val deathday: Any?,
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("profile_path")
    val profilePath: String?
) : Serializable
