package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.remote.CharacterDataRemote
import com.example.rickmortymvvm.models.Character
import io.reactivex.Observable

class CharacterRepositoryImpl(
    private val responseDataRemote: CharacterDataRemote,
    private val mapper: MapperRepository
) : CharacterRepository {

    private var page = 1
    private var pages = 1

    override fun getListCharacter(): Observable<List<Character>> {
        return if (page <= pages) {
            responseDataRemote.requestCharacterList(page)
                .doOnNext { pages = it.pages }
                .map {
                    it.characterRepository.map { characterRepositoryInfos ->
                        mapper.characterRepositoryInfosFromCharacter(characterRepositoryInfos)
                    }
                }.doOnNext { page++ }
        } else {
            return Observable.empty()
        }
    }
}
