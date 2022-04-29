package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.presentation.models.CharacterVM

sealed class PresentationCharacterListAction {
    data class GoToInfo(val characterVM: CharacterVM) : PresentationCharacterListAction()
    object Finish : PresentationCharacterListAction()
}
