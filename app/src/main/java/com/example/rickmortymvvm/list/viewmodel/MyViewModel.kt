package com.example.rickmortymvvm.list.viewmodel

import com.example.rickmortymvvm.models.Character

interface MyViewModel {
    fun onCreate()
    fun onClickCharacter(character: Character?)
    fun onClickTryAgain()
    fun onClickQuit()
    fun onScrollFinal()
}
