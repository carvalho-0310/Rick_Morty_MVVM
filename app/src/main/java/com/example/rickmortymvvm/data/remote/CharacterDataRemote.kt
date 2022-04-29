package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.repository.models.InfosRepository
import io.reactivex.Observable

interface CharacterDataRemote {
    fun requestCharacterList(page: Int): Observable<InfosRepository>
}
