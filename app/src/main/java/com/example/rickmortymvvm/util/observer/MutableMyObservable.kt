package com.example.rickmortymvvm.util.observer

import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState

interface MutableMyObservable : MyObservable {
    fun update(state: PresentationCharacterListState)

    fun update(action: PresentationCharacterListAction)
}