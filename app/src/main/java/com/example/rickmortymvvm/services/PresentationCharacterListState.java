package com.example.rickmortymvvm.services;

import java.util.List;

import com.example.rickmortymvvm.models.Character;

public class PresentationCharacterListState {

    private final boolean isLoadingVisible;
    private final List<Character> listCharacter;
    private final boolean isListCharacterVisible;
    private final boolean isShowModalErrorVisible;

    public PresentationCharacterListState(boolean isLoadingVisible, List<Character> listCharacter, boolean isListCharacterVisible, boolean isShowModalErrorVisible) {
        this.isLoadingVisible = isLoadingVisible;
        this.listCharacter = listCharacter;
        this.isListCharacterVisible = isListCharacterVisible;
        this.isShowModalErrorVisible = isShowModalErrorVisible;
    }

    public boolean isLoadingVisible() {
        return isLoadingVisible;
    }

    public List<Character> getListCharacter() {
        return listCharacter;
    }

    public boolean isListCharacterVisible() {
        return isListCharacterVisible;
    }

    public boolean isShowModalErrorVisible() {
        return isShowModalErrorVisible;
    }
}

