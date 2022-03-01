package com.example.rickmortymvvm.services.models

import io.reactivex.Observable

interface CharacterRepository {
    fun getListCharacter(): Observable<CharacterResponseVO?>
}
