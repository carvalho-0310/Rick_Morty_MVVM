package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.models.Character

sealed class PresentationCharacterListAction {
    data class GoToInfo(val character: Character) : PresentationCharacterListAction()
    object Finish : PresentationCharacterListAction()
}
