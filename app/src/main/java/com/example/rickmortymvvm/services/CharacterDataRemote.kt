package com.example.rickmortymvvm.services

import com.example.rickmortymvvm.services.models.CharacterResponseVO
import io.reactivex.Observable

interface CharacterDataRemote {
    fun requestCharacterList(page: Int): Observable<CharacterResponseVO?>
}
