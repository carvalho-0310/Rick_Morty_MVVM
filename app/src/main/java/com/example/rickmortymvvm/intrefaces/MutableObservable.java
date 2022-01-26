package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.ApresentatationCharacterListState;
import com.example.rickmortymvvm.ApresentationCharacterListAction;

public interface MutableObservable extends Observable {
    void update(ApresentatationCharacterListState apresentatationCharacterListState);

    void update(ApresentationCharacterListAction acition);
}
