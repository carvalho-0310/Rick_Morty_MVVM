package com.example.rickmortymvvm.data.remote.models

import com.google.gson.annotations.SerializedName

data class CharacterRemote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("location")
    val location: LocationRemote,
    @SerializedName("origin")
    val origin: OriginRemote
)
