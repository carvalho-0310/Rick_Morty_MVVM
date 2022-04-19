package com.example.rickmortymvvm.services

import com.example.rickmortymvvm.data.remote.CharacterDataRemoteImpl
import com.example.rickmortymvvm.data.remote.CharacterService
import com.example.rickmortymvvm.data.remote.models.CharacterResponseInfoVO
import com.example.rickmortymvvm.data.remote.models.CharacterResponseVO
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.models.Location
import com.example.rickmortymvvm.models.Origin
import com.google.common.truth.Truth.assertThat
import dev.thiagosouto.butler.file.readFile
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
    lateinit var dataRemote: CharacterDataRemoteImpl

    @Before
    fun before() {
        server = MockWebServer()
        server.start()
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dataRemote = CharacterDataRemoteImpl(retrofit.create(CharacterService::class.java))
    }

    @After
    fun after() {
        server.shutdown()
    }

    @Test
    fun `requestCharacterList - successful server call`() {
        server.enqueue(MockResponse().setBody(readFile("character/character_page_1_test_answer.json")))

        val testObserver = dataRemote.requestCharacterList(1).test()

        val request = server.takeRequest()
        assertThat(request.path).isEqualTo("/character/?page=1")
        testObserver.assertResult(expectedResult)
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
                Character(
                    1,
                    "Rick Sanchez",
                    "Alive",
                    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    "Human",
                    "",
                    "Male",
                    "2017-11-04T18:48:46.250Z",
                    Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                    Origin("Earth (C-137)", "https://rickandmortyapi.com/api/location/1")
                )
            )
        )
        const val CODE_INTERNAL_SERVER_ERROR = 500
    }
}
