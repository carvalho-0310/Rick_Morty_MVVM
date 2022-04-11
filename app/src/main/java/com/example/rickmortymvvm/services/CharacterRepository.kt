package com.example.rickmortymvvm.services

import com.example.rickmortymvvm.services.models.CharacterResponseVO
import io.reactivex.Observable

interface CharacterRepository {
    fun getListCharacter(): Observable<CharacterResponseVO?>
}
