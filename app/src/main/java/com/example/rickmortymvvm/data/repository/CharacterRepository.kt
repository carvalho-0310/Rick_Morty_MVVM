package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.models.Character
import io.reactivex.Observable

interface CharacterRepository {
    fun getListCharacter(): Observable<List<Character>>
}
