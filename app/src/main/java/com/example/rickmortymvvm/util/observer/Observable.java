package com.example.rickmortymvvm.util.observer;

import com.example.rickmortymvvm.intrefaces.Observer;

public interface Observable {
    void register(Observer observer);
}
