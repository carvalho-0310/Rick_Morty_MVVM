package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.services.PresentationCharacterListState;
import com.example.rickmortymvvm.services.PresentationCharacterListAction;

public interface Observer {
    void notify(PresentationCharacterListState state);
    void  notify(PresentationCharacterListAction action);
}
