package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.models.Character

interface PresentationCharacterListViewModel {
    fun onClickCharacter(character: Character)
    fun onClickTryAgain()
    fun onClickQuit()
    fun onScrollFinal()
    fun setUp()
}
