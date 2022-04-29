package com.example.rickmortymvvm.data.local

import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos

interface MapperLocal {

    fun characterRepositoryInfosFromCharacterLocal(characterRepositoryInfos: CharacterRepositoryInfos): CharacterLocal

    fun characterLocalFromInfosRepository(characterLocal: CharacterLocal): CharacterRepositoryInfos
}
