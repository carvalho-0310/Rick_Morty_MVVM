package com.example.rickmortymvvm.util.observer

import com.example.rickmortymvvm.intrefaces.Observer

interface MyObservable {
    fun register(observer: Observer?)
}