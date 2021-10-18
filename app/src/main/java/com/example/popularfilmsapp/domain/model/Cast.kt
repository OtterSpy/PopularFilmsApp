package com.example.popularfilmsapp.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cast(
    val character: String,
    val name: String,
    val id: Int,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("credit_id")
    val creditId: String
) : Serializable