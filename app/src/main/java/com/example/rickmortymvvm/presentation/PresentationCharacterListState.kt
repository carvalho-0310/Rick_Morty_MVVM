package com.example.rickmortymvvm.presentation.viewmodel

import com.example.rickmortymvvm.presentation.models.Character

data class PresentationCharacterListState(
    val isLoadingVisible: Boolean,
    val listCharacter: List<Character>,
    val isListCharacterVisible: Boolean,
    val isShowModalErrorVisible: Boolean,
)
