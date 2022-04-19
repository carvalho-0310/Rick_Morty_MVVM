package com.example.rickmortymvvm.data.local.character

import com.example.rickmortymvvm.models.Character
import io.reactivex.Observable

interface CharacterDataLocal {
    fun saveCharacters(list: List<Character>)

    fun getCharacters(): Observable<List<Character>>
}
