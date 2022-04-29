package com.example.rickmortymvvm.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickmortymvvm.core.RxSchedulerRule
import com.example.rickmortymvvm.core.getOrAwaitTestValue
import com.example.rickmortymvvm.data.repository.CharacterRepository
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin
import com.example.rickmortymvvm.presentation.models.CharacterVM
import com.example.rickmortymvvm.presentation.models.LocationVM
import com.example.rickmortymvvm.presentation.models.OriginVM
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PresentationCharacterListViewModelImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var repository: CharacterRepository
    private lateinit var disposable: CompositeDisposable
    private lateinit var viewModelImpl: PresentationCharacterListViewModelImpl
    private lateinit var mapperMock: MapperViewModelImpl

    @Before
    fun before() {
        repository = mockk()
        disposable = mockk(relaxed = true)
        mapperMock = mockk()
        viewModelImpl = PresentationCharacterListViewModelImpl(disposable, repository, mapperMock)
    }

    @Test
    fun `setUp - When setUp is first called Should call getListCharacter`() {
        every { repository.getListCharacter() } returns Observable.fromCallable { list }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.setUp()

        verify(exactly = 1) { repository.getListCharacter() }
    }

    @Test
    fun `setUp - When setUp is called for the second time Should not be getListCharacter`() {
        every { repository.getListCharacter() } returns Observable.fromCallable { list }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.setUp()
        viewModelImpl.setUp()

        verify(exactly = 1) { repository.getListCharacter() }
    }

    @Test
    fun `setUp - When getListCharacter is successful Should return status character list view`() {
        every { repository.getListCharacter() } returns Observable.fromCallable { list }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.setUp()
        val result = viewModelImpl.state.getOrAwaitTestValue()

        verify(exactly = 1) { repository.getListCharacter() }
        assertEquals(statusNormal, result)
    }

    @Test
    fun `setUp - When getListCharacter return list null Should return status error`() {
        every { repository.getListCharacter() } returns Observable.fromCallable { null }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.setUp()
        val result = viewModelImpl.state.getOrAwaitTestValue()

        verify(exactly = 1) { repository.getListCharacter() }
        assertEquals(stateError, result)
    }

    @Test
    fun `onClickCharacter Should warn the character clicked`() {
        viewModelImpl.onClickCharacter(characterVM[0])

        val result = viewModelImpl.action.getOrAwaitTestValue()

        assertEquals(PresentationCharacterListAction.GoToInfo(characterVM[0]), result)
    }

    @Test
    fun `onClickTryAgain Should get list character successfully`() {
        every { repository.getListCharacter() } returns Observable.fromCallable { list }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.onClickTryAgain()

        verify(exactly = 1) { repository.getListCharacter() }
    }

    @Test
    fun `onClickQuit Should return action finish`() {
        viewModelImpl.onClickQuit()

        val result = viewModelImpl.action.getOrAwaitTestValue()

        assertEquals(PresentationCharacterListAction.Finish, result)
    }

    @Test
    fun `onScrollFinal Should get list character successfully`() {
        every { repository.getListCharacter() } returns Observable.fromCallable { list }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.onScrollFinal()

        verify(exactly = 1) { repository.getListCharacter() }
    }

    @Test
    fun `onScrollFinal - When requestCharacterList return exception Should return status error`() {
        every { repository.getListCharacter() } returns Observable.error { Exception() }
        every { mapperMock.characterFromCharacterVm(list[0]) } returns characterVM[0]

        viewModelImpl.onScrollFinal()
        val result = viewModelImpl.state.getOrAwaitTestValue()

        assertEquals(stateError, result)
        verify(exactly = 1) { repository.getListCharacter() }
    }

    companion object {
        val list = listOf(
            Character(
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
        )

        val characterVM = listOf(
            CharacterVM(
                1,
                "name",
                "status",
                "image",
                "species",
                "type",
                "gender",
                "created",
                LocationVM("location name", "location ur"),
                OriginVM("origin name", "origin ur")
            )
        )

        val stateError = PresentationCharacterListState(
            false,
            emptyList(),
            false,
            true
        )
        val statusNormal = PresentationCharacterListState(
            false,
            characterVM,
            true,
            false
        )
    }
}
