package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.local.CharacterDataLocal
import com.example.rickmortymvvm.data.remote.CharacterDataRemote
import com.example.rickmortymvvm.models.Character
import io.reactivex.Observable

class CharacterRepositoryImpl(
    private val responseDataRemote: CharacterDataRemote,
    private val mapper: MapperRepository,
    private val characterDao: CharacterDataLocal
) : CharacterRepository {

    private var page = 1
    private var pages = 1

    override fun getListCharacter(): Observable<List<Character>> {
        return if (page <= pages) {
            responseDataRemote.requestCharacterList(page)
                .doOnNext { pages = it.pages }
                .doOnComplete { page++ }
                .map {
                    if (page == 1) characterDao.clearLocalList()
                    characterDao.saveCharacters(it.characterRepository)
                    it.characterRepository.map { listCharacter ->
                        mapper.characterRepositoryInfosFromCharacter(listCharacter)
                    }
                }.onErrorResumeNext(
                    characterDao.getCharacters()
                        .flatMap {
                            if (page == 1) {
                                if (it.isEmpty())
                                    Observable.error(Exception())
                                else
                                    Observable.just(it.map(mapper::characterRepositoryInfosFromCharacter))
                            } else Observable.error(Exception())
                        }
                )
        } else Observable.empty()
    }
}
