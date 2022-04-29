package com.example.rickmortymvvm.data.remote.models

import com.google.gson.annotations.SerializedName

data class CharacterResponseVO(
    @SerializedName("info")
    val info: CharacterResponseInfoVO,
    @SerializedName("results")
    val results: List<CharacterRemote>
)
