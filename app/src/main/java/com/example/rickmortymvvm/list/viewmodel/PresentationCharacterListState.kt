package com.example.rickmortymvvm.list.viewmodel

import com.example.rickmortymvvm.models.Character

class PresentationCharacterListState(
    val isLoadingVisible: Boolean,
    val listCharacter: List<Character>,
    val isListCharacterVisible: Boolean,
    val isShowModalErrorVisible: Boolean,
)
