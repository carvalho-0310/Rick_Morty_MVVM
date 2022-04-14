package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import com.example.rickmortymvvm.data.remote.CharacterDataRemote
import io.reactivex.Observable

class CharacterRepositoryImpl(
        private val responseDataRemote: CharacterDataRemote,
) : CharacterRepository {

    private var page = 1
    private var pages = 1

    override fun getListCharacter(): Observable<CharacterResponseVO?> {
        return if (page <= pages) {
            responseDataRemote.requestCharacterList(page)
                .doOnNext { it?.info?.pages?.let { p -> pages = p } }
                .doOnComplete { page++ }
        } else {
            Observable.empty()
        }
    }
}
