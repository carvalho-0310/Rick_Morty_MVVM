package com.example.rickmortymvvm.list.viewmodel

import com.example.rickmortymvvm.models.Character

interface PresentationCharacterListViewModel {
    fun onClickCharacter(character: Character)
    fun onClickTryAgain()
    fun onClickQuit()
    fun onScrollFinal()
}
