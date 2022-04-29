package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin

class MapperRepositoryImpl : MapperRepository {
    override fun characterRepositoryInfosFromCharacter(characterRepositoryInfos: CharacterRepositoryInfos): Character {
        return Character(
            characterRepositoryInfos.id,
            characterRepositoryInfos.name,
            characterRepositoryInfos.status,
            characterRepositoryInfos.image,
            characterRepositoryInfos.species,
            characterRepositoryInfos.type,
            characterRepositoryInfos.gender,
            characterRepositoryInfos.created,
            Location(characterRepositoryInfos.locationName, characterRepositoryInfos.locationUrl),
            Origin(characterRepositoryInfos.originName, characterRepositoryInfos.originUrl)
        )
    }
}
