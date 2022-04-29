package com.example.rickmortymvvm.data.repository

import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperRepositoryImplTest {
    private val mapper = MapperRepositoryImpl()
    @Test
    fun characterRepositoryInfosFromCharacter() {
        val expectedResult = mapper.characterRepositoryInfosFromCharacter(characterRepositoryInfos)

        assertEquals(expectedResult.id, character.id)
        assertEquals(expectedResult.name, character.name)
        assertEquals(expectedResult.status, character.status)
        assertEquals(expectedResult.image, character.image)
        assertEquals(expectedResult.type, character.type)
        assertEquals(expectedResult.gender, character.gender)
        assertEquals(expectedResult.created, character.created)
        assertEquals(expectedResult.location.name, character.location.name)
        assertEquals(expectedResult.location.url, character.location.url)
        assertEquals(expectedResult.origin.name, character.origin.name)
        assertEquals(expectedResult.origin.url, character.origin.url)
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
                "origin url",
            )
        val character = Character(
            1,
            "name",
            "status",
            "image",
            "species",
            "type",
            "gender",
            "created",
            Location(
                "local name",
                "local url"
            ),
            Origin(
                "origin name",
                "origin url"
            )
        )
    }
}
