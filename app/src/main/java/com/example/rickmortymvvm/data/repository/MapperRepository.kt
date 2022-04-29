package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.models.Character

interface MapperRepository {
    fun characterRepositoryInfosFromCharacter(characterRepositoryInfos: CharacterRepositoryInfos): Character
}
