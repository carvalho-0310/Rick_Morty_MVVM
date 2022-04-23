package com.example.rickmortymvvm.data.local.character

import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.internal.operators.observable.ObservableFromCallable
import org.junit.Before
import org.junit.Test

class CharacterDataLocalImplTest {

    private lateinit var characterLocalDaoMock: CharacterLocalDao
    private lateinit var characterDataLocal: CharacterDataLocalImpl

    @Before
    fun before() {
        characterLocalDaoMock = mockk()
        characterDataLocal = CharacterDataLocalImpl(characterLocalDaoMock)
    }

    @Test
    fun `testSaveCharacters - Should save the character list`() {
        every { characterLocalDaoMock.savaCharacter(any()) } returns Unit

        characterDataLocal.saveCharacters(expectedResult)

        verify(exactly = 1) { characterLocalDaoMock.savaCharacter(expectedSava) }
    }

    @Test
    fun `testGetCharacters - Should get the character list`() {
        every { characterLocalDaoMock.getList() } returns returnsLocalDao

        val listLocal = characterDataLocal.getCharacters().test()

        listLocal.assertValue(expectedResult)
    }

    companion object {
        val expectedResult = listOf(
            Character(
                1,
                "name",
                "status",
                "image",
                "specie",
                "type",
                "gender",
                "created",
                Location("location name", "location url"),
                Origin("origin name", "origin url")
            )
        )
        val returnsLocalDao = ObservableFromCallable {
            listOf(
                CharacterLocal(
                    1,
                    "name",
                    "status",
                    "image",
                    "specie",
                    "type",
                    "gender",
                    "created",
                    "location name",
                    "location url",
                    "origin name",
                    "origin url",
                )
            )
        }
        val expectedSava = CharacterLocal(
            1,
            "name",
            "status",
            "image",
            "specie",
            "type",
            "gender",
            "created",
            "location name",
            "location url",
            "origin name",
            "origin url"
        )
    }
}
