package com.example.rickmortymvvm.services.models

import com.example.rickmortymvvm.models.Character

data class CharacterResponseVO(
    val info: CharacterResponseInfoVO? = null,
    val results: List<Character>? = null,
)
