package com.example.rickmortymvvm.services

import com.example.rickmortymvvm.services.models.CharacterResponseVO

import io.reactivex.Observable

class CharacterDataRemoteImpl(private val service: CharacterService) : CharacterDataRemote {

    override fun requestCharacterList(page: Int): Observable<CharacterResponseVO?> {
        return service.listCharacter(page)
    }
}
