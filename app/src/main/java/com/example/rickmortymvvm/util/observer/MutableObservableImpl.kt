package com.example.rickmortymvvm.util.observer

import com.example.rickmortymvvm.intrefaces.Observer
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListAction
import com.example.rickmortymvvm.list.viewmodel.PresentationCharacterListState

class MutableObservableImpl : MutableObservable {
    private var observer: Observer? = null
    override fun update(state: PresentationCharacterListState) {
        observer!!.notify(state)
    }

    override fun update(action: PresentationCharacterListAction?) {
        observer!!.notify(action)
    }

    override fun register(observer: Observer?) {
        this.observer = observer
    }


}