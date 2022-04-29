package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.repository.models.InfosRepository

import io.reactivex.Observable

class CharacterDataRemoteImpl(
    private val service: CharacterService,
    private val mapperRemote: MapperRemote
) : CharacterDataRemote {

    override fun requestCharacterList(page: Int): Observable<InfosRepository> {
        return service.listCharacter(page).map {
            mapperRemote.responseFromInfosRepository(it)
        }
    }
}
