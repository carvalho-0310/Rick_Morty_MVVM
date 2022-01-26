package com.example.rickmortymvvm;

import java.util.List;

public class ApresentatationCharacterListState {

    private final boolean isLoadingVisible;
    private final List<Character> listChracter ;
    private final boolean isListCharacterVisible;
    private final boolean isShowModalErrorVisible;

    public ApresentatationCharacterListState(boolean isLoadingVisible, List<Character> listChracter, boolean isListCharacterVisible, boolean isShowModalErrorVisible) {
        this.isLoadingVisible = isLoadingVisible;
        this.listChracter = listChracter;
        this.isListCharacterVisible = isListCharacterVisible;
        this.isShowModalErrorVisible = isShowModalErrorVisible;
    }

    public boolean isLoadingVisible() {
        return isLoadingVisible;
    }

    public List<Character> getListChracter() {
        return listChracter;
    }

    public boolean isListCharacterVisible() {
        return isListCharacterVisible;
    }

    public boolean isShowModalErrorVisible() {
        return isShowModalErrorVisible;
    }
}

