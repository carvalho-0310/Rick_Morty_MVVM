package com.example.rickmortymvvm.data.models

import com.example.rickmortymvvm.presentation.models.Character

data class CharacterResponseVO(
        val info: CharacterResponseInfoVO? = null,
        val results: List<Character>? = null,
)
