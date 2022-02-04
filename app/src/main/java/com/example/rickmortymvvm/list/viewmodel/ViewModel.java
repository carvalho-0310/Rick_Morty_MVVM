package com.example.rickmortymvvm.list.viewmodel;

import com.example.rickmortymvvm.util.observer.Observable;
import com.example.rickmortymvvm.models.Character;

public interface ViewModel {
    void onCreate();

    void onClickCharacter(Character character);

    void onClickTryAgain();

    void onClickQuit();

    Observable getObservable();

    void onScrollFinal();
}
