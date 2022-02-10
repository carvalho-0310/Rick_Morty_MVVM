package com.example.rickmortymvvm.util.observer

import com.example.rickmortymvvm.intrefaces.Observer

interface Observable {
    fun register(observer: Observer?)
}