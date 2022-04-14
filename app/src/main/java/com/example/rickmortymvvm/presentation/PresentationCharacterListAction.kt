package com.example.rickmortymvvm.presentation.viewmodel

import com.example.rickmortymvvm.presentation.models.Character

sealed class PresentationCharacterListAction {
    data class GoToInfo(val character: Character) : PresentationCharacterListAction()
    object Finish : PresentationCharacterListAction()
}
