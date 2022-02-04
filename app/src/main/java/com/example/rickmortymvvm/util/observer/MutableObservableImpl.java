package com.example.rickmortymvvm.util.observer;


import com.example.rickmortymvvm.intrefaces.Observer;
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction;
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState;

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
