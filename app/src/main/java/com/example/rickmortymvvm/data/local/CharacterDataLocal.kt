package com.example.rickmortymvvm.data.local

import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import io.reactivex.Observable

interface CharacterDataLocal {

    fun saveCharacters(list: List<CharacterRepositoryInfos>)

    fun getCharacters(): Observable<List<CharacterRepositoryInfos>>

    fun clearLocalList()
}
