package com.example.rickmortymvvm.list.viewmodel

import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.util.observer.MyObservable

interface MyViewModel {
    fun onCreate()
    fun onClickCharacter(character: Character?)
    fun onClickTryAgain()
    fun onClickQuit()
    val myObservable: MyObservable?
    fun onScrollFinal()

}