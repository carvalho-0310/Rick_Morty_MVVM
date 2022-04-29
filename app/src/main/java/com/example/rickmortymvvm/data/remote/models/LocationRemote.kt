package com.example.rickmortymvvm.data.remote.models

import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
