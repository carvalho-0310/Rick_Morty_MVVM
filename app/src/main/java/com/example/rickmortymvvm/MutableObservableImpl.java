package com.example.rickmortymvvm;


import com.example.rickmortymvvm.intrefaces.MutableObservable;
import com.example.rickmortymvvm.intrefaces.Observer;

public class MutableObservableImpl implements MutableObservable {
    private Observer observer;

    @Override
    public void update(PresentationCharacterListState state) {
        observer.notify(state);
    }

    @Override
    public void update(PresentationCharacterListAction action) {
        observer.notify(action);
    }

    @Override
    public void register(Observer observer) {
        this.observer = observer;
    }
}
