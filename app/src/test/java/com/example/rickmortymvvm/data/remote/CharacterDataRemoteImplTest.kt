package com.example.rickmortymvvm.data.remote

import com.example.rickmortymvvm.data.remote.models.OriginRemote
import com.example.rickmortymvvm.data.remote.models.LocationRemote
import com.example.rickmortymvvm.data.remote.models.CharacterRemote
import com.example.rickmortymvvm.data.remote.models.CharacterResponseInfoVO
import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import com.example.rickmortymvvm.data.repository.models.CharacterRepositoryInfos
import com.example.rickmortymvvm.data.repository.models.InfosRepository
import com.google.common.truth.Truth.assertThat
import dev.thiagosouto.butler.file.readFile
import io.mockk.every
import io.mockk.mockk
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.HttpException
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharacterDataRemoteImplTest {

    lateinit var server: MockWebServer
    lateinit var retrofit: Retrofit
    private lateinit var dataRemote: CharacterDataRemoteImpl
    lateinit var mapperMock: MapperRemote

    @Before
    fun before() {
        server = MockWebServer()
        server.start()
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mapperMock = mockk()
        dataRemote = CharacterDataRemoteImpl(retrofit.create(CharacterService::class.java), mapperMock)
    }

    @After
    fun after() {
        server.shutdown()
    }

    @Test
    fun `requestCharacterList - Should get a list of characters in the right url`() {
        every { mapperMock.responseFromInfosRepository(expectedResult) } returns expectedResultMapper
        server.enqueue(MockResponse().setBody(readFile("character/character_page_1_test_answer.json")))

        val testObserver = dataRemote.requestCharacterList(1).test()

        val request = server.takeRequest()
        assertThat(request.path).isEqualTo("/character/?page=1")
        testObserver.assertResult(expectedResultMapper)
    }

    @Test
    fun `requestCharacterList - When returning status code 500 Should return an HttpException`() {
        server.enqueue(MockResponse().setResponseCode(CODE_INTERNAL_SERVER_ERROR))

        val testObserver = dataRemote.requestCharacterList(1).test()

        testObserver.assertFailure(HttpException::class.java)
    }

    private companion object {
        val expectedResult = CharacterResponseVO(
            CharacterResponseInfoVO(42),
            listOf(
                CharacterRemote(
                    1,
                    "Rick Sanchez",
                    "Alive",
                    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    "Human",
                    "",
                    "Male",
                    "2017-11-04T18:48:46.250Z",
                    LocationRemote(
                        "Citadel of Ricks",
                        "https://rickandmortyapi.com/api/location/3"
                    ),
                    OriginRemote(
                        "Earth (C-137)",
                        "https://rickandmortyapi.com/api/location/1"
                    )
                )
            )
        )

        val expectedResultMapper = InfosRepository(
            42,
            listOf(
                CharacterRepositoryInfos(
                    1,
                    "Rick Sanchez",
                    "Alive",
                    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    "Human",
                    "",
                    "Male",
                    "2017-11-04T18:48:46.250Z",
                    "Citadel of Ricks",
                    "https://rickandmortyapi.com/api/location/3",
                    "Earth (C-137)",
                    "https://rickandmortyapi.com/api/location/1"
                )
            )
        )

        const val CODE_INTERNAL_SERVER_ERROR = 500
    }
}
