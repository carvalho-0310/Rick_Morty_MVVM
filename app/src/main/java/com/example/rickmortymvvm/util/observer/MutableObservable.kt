package com.example.rickmortymvvm.util.observer

import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState

interface MutableObservable : Observable {
    fun update(state: PresentationCharacterListState)

    fun update(action: PresentationCharacterListAction?)
}