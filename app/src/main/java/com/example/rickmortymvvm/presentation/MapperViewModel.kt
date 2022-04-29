package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.presentation.models.CharacterVM

interface MapperViewModel {
    fun characterFromCharacterVm(character: Character): CharacterVM
}
