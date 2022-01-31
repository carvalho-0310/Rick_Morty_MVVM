package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.PresentationCharacterListState;
import com.example.rickmortymvvm.PresentationCharacterListAction;

public interface Observer {
    void notify(PresentationCharacterListState state);
    void  notify(PresentationCharacterListAction action);
}
