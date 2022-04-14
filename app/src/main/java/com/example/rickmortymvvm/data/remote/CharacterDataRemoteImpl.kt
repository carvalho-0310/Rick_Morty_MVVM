package com.example.rickmortymvvm.data.repository.remote

import com.example.rickmortymvvm.data.models.CharacterResponseVO

import io.reactivex.Observable

class CharacterDataRemoteImpl(private val service: CharacterService) : CharacterDataRemote {

    override fun requestCharacterList(page: Int): Observable<CharacterResponseVO?> {
        return service.listCharacter(page)
    }
}