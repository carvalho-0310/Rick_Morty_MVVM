package com.example.rickmortymvvm.intrefaces

import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState

interface Observer {
    fun notify(state: PresentationCharacterListState)
    fun notify(action: PresentationCharacterListAction?)
}