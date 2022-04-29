package com.example.rickmortymvvm.data.local

import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos

class MapperLocalImpl : MapperLocal {

    override fun characterRepositoryInfosFromCharacterLocal(characterRepositoryInfos: CharacterRepositoryInfos): CharacterLocal {
        return CharacterLocal(
            characterRepositoryInfos.id,
            characterRepositoryInfos.name,
            characterRepositoryInfos.status,
            characterRepositoryInfos.image,
            characterRepositoryInfos.species,
            characterRepositoryInfos.type,
            characterRepositoryInfos.gender,
            characterRepositoryInfos.created,
            characterRepositoryInfos.locationName,
            characterRepositoryInfos.locationUrl,
            characterRepositoryInfos.originName,
            characterRepositoryInfos.originUrl
        )
    }

    override fun characterLocalFromInfosRepository(characterLocal: CharacterLocal): CharacterRepositoryInfos {
        return CharacterRepositoryInfos(
            characterLocal.id,
            characterLocal.name,
            characterLocal.status,
            characterLocal.image,
            characterLocal.species,
            characterLocal.type,
            characterLocal.gender,
            characterLocal.created,
            characterLocal.locationName,
            characterLocal.locationUrl,
            characterLocal.originName,
            characterLocal.originUrl
        )
    }
}
