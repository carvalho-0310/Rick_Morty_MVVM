package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import io.reactivex.Observable

interface CharacterDataRemote {
    fun requestCharacterList(page: Int): Observable<CharacterResponseVO?>
}
