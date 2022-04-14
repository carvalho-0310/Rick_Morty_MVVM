package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import io.reactivex.Observable

interface CharacterRepository {
    fun getListCharacter(): Observable<CharacterResponseVO?>
}
