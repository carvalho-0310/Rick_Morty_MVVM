package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.remote.models.CharacterRemote
import com.example.rickmortymvvm.data.remote.models.OriginRemote
import com.example.rickmortymvvm.data.remote.models.CharacterResponseInfoVO
import com.example.rickmortymvvm.data.remote.models.LocationRemote
import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.data.repository.models.InfosRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperRemoteImplTest {

    private val mapper = MapperRemoteImpl()

    @Test
    fun characterRemoteFromCharacterRepositoryInfos() {
        val expectedResult = mapper.characterRemoteFromCharacterRepositoryInfos(response.results[0])

        assertEquals(expectedResult.id, infosRepository.characterRepository[0].id)
        assertEquals(expectedResult.name, infosRepository.characterRepository[0].name)
        assertEquals(expectedResult.status, infosRepository.characterRepository[0].status)
        assertEquals(expectedResult.image, infosRepository.characterRepository[0].image)
        assertEquals(expectedResult.species, infosRepository.characterRepository[0].species)
        assertEquals(expectedResult.type, infosRepository.characterRepository[0].type)
        assertEquals(expectedResult.gender, infosRepository.characterRepository[0].gender)
        assertEquals(expectedResult.created, infosRepository.characterRepository[0].created)
        assertEquals(expectedResult.locationName, infosRepository.characterRepository[0].locationName)
        assertEquals(expectedResult.locationUrl, infosRepository.characterRepository[0].locationUrl)
        assertEquals(expectedResult.originName, infosRepository.characterRepository[0].originName)
        assertEquals(expectedResult.originUrl, infosRepository.characterRepository[0].originUrl)
    }

    @Test
    fun responseFromInfosRepository() {
        val expectedResult = mapper.responseFromInfosRepository(response)

        assertEquals(expectedResult.pages, infosRepository.pages)
        assertEquals(expectedResult.characterRepository[0].id, infosRepository.characterRepository[0].id)
        assertEquals(expectedResult.characterRepository[0].name, infosRepository.characterRepository[0].name)
        assertEquals(expectedResult.characterRepository[0].status, infosRepository.characterRepository[0].status)
        assertEquals(expectedResult.characterRepository[0].image, infosRepository.characterRepository[0].image)
        assertEquals(expectedResult.characterRepository[0].species, infosRepository.characterRepository[0].species)
        assertEquals(expectedResult.characterRepository[0].type, infosRepository.characterRepository[0].type)
        assertEquals(expectedResult.characterRepository[0].gender, infosRepository.characterRepository[0].gender)
        assertEquals(expectedResult.characterRepository[0].created, infosRepository.characterRepository[0].created)
        assertEquals(expectedResult.characterRepository[0].locationName, infosRepository.characterRepository[0].locationName)
        assertEquals(expectedResult.characterRepository[0].locationUrl, infosRepository.characterRepository[0].locationUrl)
        assertEquals(expectedResult.characterRepository[0].originName, infosRepository.characterRepository[0].originName)
        assertEquals(expectedResult.characterRepository[0].originUrl, infosRepository.characterRepository[0].originUrl)
    }

    companion object {
        val response = CharacterResponseVO(
            CharacterResponseInfoVO(1),
            listOf(
                CharacterRemote(
                    1,
                    "name",
                    "status",
                    "image",
                    "species",
                    "type",
                    "gender",
                    "created",
                    LocationRemote(
                        "local name",
                        "local url"
                    ),
                    OriginRemote(
                        "origin name",
                        "origin url"
                    )
                )
            )
        )

        val infosRepository = InfosRepository(
            1,
            listOf(
                CharacterRepositoryInfos(
                    1,
                    "name",
                    "status",
                    "image",
                    "species",
                    "type",
                    "gender",
                    "created",
                    "local name",
                    "local url",
                    "origin name",
                    "origin url"
                )
            )
        )
    }
}
