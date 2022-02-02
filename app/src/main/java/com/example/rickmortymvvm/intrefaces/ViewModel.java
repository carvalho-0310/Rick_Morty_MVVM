package com.example.rickmortymvvm.intrefaces;

import com.example.rickmortymvvm.models.Character;

public interface ViewModel {
    void onCreate();

    void onClickCharacter(Character character);

    void onClickTryAgain();

    void onClickQuit();

    Observable getObservable();
}
