package com.example.rickmortymvvm.data.remote.models

import com.example.rickmortymvvm.models.Character

data class CharacterResponseVO(
    val info: CharacterResponseInfoVO,
    val results: List<Character>
)
