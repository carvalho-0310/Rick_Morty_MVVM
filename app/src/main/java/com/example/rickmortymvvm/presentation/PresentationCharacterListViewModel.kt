package com.example.rickmortymvvm.presentation.viewmodel

import com.example.rickmortymvvm.presentation.models.Character

interface PresentationCharacterListViewModel {
    fun onClickCharacter(character: Character)
    fun onClickTryAgain()
    fun onClickQuit()
    fun onScrollFinal()
}
