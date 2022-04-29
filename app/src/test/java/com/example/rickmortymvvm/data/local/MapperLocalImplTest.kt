package com.example.rickmortymvvm.data.local

import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import org.junit.Assert.assertEquals

import org.junit.Test

class MapperLocalImplTest {

    private val mapper = MapperLocalImpl()

    @Test
    fun characterRepositoryInfosFromCharacterLocal() {
        val expectedResult = mapper.characterRepositoryInfosFromCharacterLocal(characterRepositoryInfos)

        assertEquals(expectedResult.id, characterLocal.id)
        assertEquals(expectedResult.name, characterLocal.name)
        assertEquals(expectedResult.status, characterLocal.status)
        assertEquals(expectedResult.image, characterLocal.image)
        assertEquals(expectedResult.species, characterLocal.species)
        assertEquals(expectedResult.type, characterLocal.type)
        assertEquals(expectedResult.gender, characterLocal.gender)
        assertEquals(expectedResult.created, characterLocal.created)
        assertEquals(expectedResult.locationName, characterLocal.locationName)
        assertEquals(expectedResult.locationUrl, characterLocal.locationUrl)
        assertEquals(expectedResult.originName, characterLocal.originName)
        assertEquals(expectedResult.originUrl, characterLocal.originUrl)
    }

    @Test
    fun characterLocalFromInfosRepository() {
        val expectedResult = mapper.characterLocalFromInfosRepository(characterLocal)

        assertEquals(expectedResult.id, characterRepositoryInfos.id)
        assertEquals(expectedResult.name, characterRepositoryInfos.name)
        assertEquals(expectedResult.status, characterRepositoryInfos.status)
        assertEquals(expectedResult.image, characterRepositoryInfos.image)
        assertEquals(expectedResult.species, characterRepositoryInfos.species)
        assertEquals(expectedResult.type, characterRepositoryInfos.type)
        assertEquals(expectedResult.gender, characterRepositoryInfos.gender)
        assertEquals(expectedResult.created, characterRepositoryInfos.created)
        assertEquals(expectedResult.locationName, characterRepositoryInfos.locationName)
        assertEquals(expectedResult.locationUrl, characterRepositoryInfos.locationUrl)
        assertEquals(expectedResult.originName, characterRepositoryInfos.originName)
        assertEquals(expectedResult.originUrl, characterRepositoryInfos.originUrl)
    }
    companion object {
        val characterRepositoryInfos =
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
        val characterLocal = CharacterLocal(
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
    }
}
