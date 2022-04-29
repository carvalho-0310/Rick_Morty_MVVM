package com.example.rickmortymvvm.data.local.character

import com.example.rickmortymvvm.data.local.MapperLocal
import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.internal.operators.observable.ObservableFromCallable
import org.junit.Before
import org.junit.Test

class CharacterDataLocalImplTest {

    private lateinit var characterLocalDaoMock: CharacterLocalDao
    private lateinit var mapperMockImpl: MapperLocal
    private lateinit var characterDataLocal: CharacterDataLocalImpl

    @Before
    fun before() {
        characterLocalDaoMock = mockk()
        mapperMockImpl = mockk()
        characterDataLocal = CharacterDataLocalImpl(characterLocalDaoMock, mapperMockImpl)
    }

    @Test
    fun `testSaveCharacters - Should save the character list`() {
        every { characterLocalDaoMock.savaCharacter(any()) } returns Unit
        every { mapperMockImpl.characterRepositoryInfosFromCharacterLocal(expectedResult[0]) } returns characterLocal

        characterDataLocal.saveCharacters(expectedResult)

        verify(exactly = 1) { characterLocalDaoMock.savaCharacter(characterLocal) }
    }

    @Test
    fun `testGetCharacters - Should get the character list`() {
        every { characterLocalDaoMock.getList() } returns returnsLocalDao
        every { mapperMockImpl.characterLocalFromInfosRepository(characterLocal) } returns expectedResult[0]

        val listLocal = characterDataLocal.getCharacters().test()

        listLocal.assertValue(expectedResult)
    }

    companion object {
        val expectedResult = listOf(
            CharacterRepositoryInfos(
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
        val characterLocal = CharacterLocal(
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
