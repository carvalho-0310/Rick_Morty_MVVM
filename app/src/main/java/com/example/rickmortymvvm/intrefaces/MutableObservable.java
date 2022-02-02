package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.services.PresentationCharacterListState;
import com.example.rickmortymvvm.services.PresentationCharacterListAction;

public interface MutableObservable extends Observable {
    void update(PresentationCharacterListState state);

    void update(PresentationCharacterListAction action);
}
