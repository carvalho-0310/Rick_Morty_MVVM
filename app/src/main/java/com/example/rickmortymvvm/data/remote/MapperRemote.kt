package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.remote.models.CharacterRemote
import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.data.repository.models.InfosRepository

interface MapperRemote {

    fun responseFromInfosRepository(response: CharacterResponseVO): InfosRepository

    fun characterRemoteFromCharacterRepositoryInfos(remote: CharacterRemote): CharacterRepositoryInfos
}
