package com.example.rickmortymvvm.data.remote.models

import com.example.rickmortymvvm.models.Character
import com.google.gson.annotations.SerializedName

data class CharacterResponseVO(
        @SerializedName("info")
    val info: CharacterResponseInfoVO? = null,
        @SerializedName("results")
    val results: List<Character>? = null,
)
