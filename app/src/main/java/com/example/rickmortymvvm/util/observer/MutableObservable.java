package com.example.rickmortymvvm.util.observer;

import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState;
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction;

public interface MutableObservable extends Observable {
    void update(PresentationCharacterListState state);

    void update(PresentationCharacterListAction action);
}
