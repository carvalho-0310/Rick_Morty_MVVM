package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.remote.models.CharacterRemote
import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.data.repository.models.InfosRepository

class MapperRemoteImpl : MapperRemote {

    override fun responseFromInfosRepository(response: CharacterResponseVO): InfosRepository {
        return InfosRepository(
            response.info.pages,
            response.results.map {
                characterRemoteFromCharacterRepositoryInfos(it)
            }
        )
    }

    override fun characterRemoteFromCharacterRepositoryInfos(remote: CharacterRemote): CharacterRepositoryInfos {
        return CharacterRepositoryInfos(
            remote.id,
            remote.name,
            remote.status,
            remote.image,
            remote.species,
            remote.type,
            remote.gender,
            remote.created,
            remote.location.name,
            remote.location.url,
            remote.origin.name,
            remote.origin.url
        )
    }
}
