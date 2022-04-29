package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.presentation.models.CharacterVM

interface PresentationCharacterListViewModel {
    fun onClickCharacter(characterVM: CharacterVM)
    fun onClickTryAgain()
    fun onClickQuit()
    fun onScrollFinal()
    fun setUp()
}
