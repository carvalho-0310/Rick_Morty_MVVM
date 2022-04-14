package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.models.Character

data class PresentationCharacterListState(
    val isLoadingVisible: Boolean,
    val listCharacter: List<Character>,
    val isListCharacterVisible: Boolean,
    val isShowModalErrorVisible: Boolean,
)
