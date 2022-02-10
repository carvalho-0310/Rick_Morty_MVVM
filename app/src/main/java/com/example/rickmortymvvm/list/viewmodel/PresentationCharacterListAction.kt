package com.example.rickmortymvvm.list.viewmodel

import com.example.rickmortymvvm.models.Character

abstract class PresentationCharacterListAction {
    class GoToInfo(val character: Character) : PresentationCharacterListAction()
    class Finish : PresentationCharacterListAction()
}