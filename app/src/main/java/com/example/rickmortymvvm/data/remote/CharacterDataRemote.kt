package com.example.rickmortymvvm.data.repository.remote

import com.example.rickmortymvvm.data.models.CharacterResponseVO
import io.reactivex.Observable

interface CharacterDataRemote {
    fun requestCharacterList(page: Int): Observable<CharacterResponseVO?>
}
