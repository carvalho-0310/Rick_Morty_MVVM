package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.presentation.models.CharacterVM

data class PresentationCharacterListState(
    val isLoadingVisible: Boolean,
    val listCharacterVM: List<CharacterVM>,
    val isListCharacterVisible: Boolean,
    val isShowModalErrorVisible: Boolean,
)
