package com.example.rickmortymvvm.services.models

import com.example.rickmortymvvm.services.CharacterDataRemote
import io.reactivex.Observable

class CharacterRepositoryImpl(
    private val responseDataRemote: CharacterDataRemote,
) : CharacterRepository {

    private var page = 1
    private var pages = 1

    override fun getListCharacter(): Observable<CharacterResponseVO?> {
        return if (page <= pages) {
            responseDataRemote.requestCharacterList(page)
                .doOnNext { pages = it?.info?.pages!! }
                .doOnComplete { page++ }
        } else {
            Observable.empty()
        }
    }
}
