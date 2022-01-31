package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.PresentationCharacterListState;
import com.example.rickmortymvvm.PresentationCharacterListAction;

public interface MutableObservable extends Observable {
    void update(PresentationCharacterListState presentationCharacterListState);

    void update(PresentationCharacterListAction action);
}
