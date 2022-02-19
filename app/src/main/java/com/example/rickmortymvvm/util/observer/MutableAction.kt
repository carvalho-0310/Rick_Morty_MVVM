package com.example.rickmortymvvm.util.observer

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MutableAction<T> : MutableLiveData<T>() {
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }
        super.observe(owner, Observer { action ->
            if (action == null) return@Observer
            observer.onChanged(action)
            value = null

        })
    }
    @MainThread
    fun sendAction(action: T) {
        value = action
    }


}
