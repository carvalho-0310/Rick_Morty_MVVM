package com.example.rickmortymvvm.presentation

import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin
import com.example.rickmortymvvm.presentation.models.CharacterVM
import com.example.rickmortymvvm.presentation.models.LocationVM
import com.example.rickmortymvvm.presentation.models.OriginVM
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperViewModelImplTest {
    private val mapper = MapperViewModelImpl()

    @Test
    fun characterFromCharacterVm() {
        val expectedResult = mapper.characterFromCharacterVm(character)

        assertEquals(expectedResult.id, characterVM.id)
        assertEquals(expectedResult.name, characterVM.name)
        assertEquals(expectedResult.status, characterVM.status)
        assertEquals(expectedResult.image, characterVM.image)
        assertEquals(expectedResult.species, characterVM.species)
        assertEquals(expectedResult.type, characterVM.type)
        assertEquals(expectedResult.gender, characterVM.gender)
        assertEquals(expectedResult.created, characterVM.created)
        assertEquals(expectedResult.locationVM.name, characterVM.locationVM.name)
        assertEquals(expectedResult.locationVM.url, characterVM.locationVM.url)
        assertEquals(expectedResult.originVM.name, characterVM.originVM.name)
        assertEquals(expectedResult.originVM.url, characterVM.originVM.url)
    }
    companion object {
        val character = Character(
            1,
            "name",
            "status",
            "image",
            "species",
            "type",
            "gender",
            "created",
            Location("location name", "location url"),
            Origin("origin name", "origin url")
        )
        val characterVM = CharacterVM(
            1,
            "name",
            "status",
            "image",
            "species",
            "type",
            "gender",
            "created",
            LocationVM("location name", "location url"),
            OriginVM("origin name", "origin url")
        )
    }
}
