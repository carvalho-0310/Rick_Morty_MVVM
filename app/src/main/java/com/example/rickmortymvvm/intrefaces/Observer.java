package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState;
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction;

public interface Observer {
    void notify(PresentationCharacterListState state);

    void notify(PresentationCharacterListAction action);
}
