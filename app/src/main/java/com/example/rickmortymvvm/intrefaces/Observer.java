package com.example.rickmortymvvm.intrefaces;


import com.example.rickmortymvvm.ApresentatationCharacterListState;
import com.example.rickmortymvvm.ApresentationCharacterListAction;

public interface Observer {
    void notify(ApresentatationCharacterListState state);
    void  notify(ApresentationCharacterListAction action);
}
