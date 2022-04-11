package com.example.rickmortymvvm.services.models

import android.annotation.SuppressLint
import com.example.rickmortymvvm.models.Character
import com.example.rickmortymvvm.services.CharacterDataRemoteImpl
import com.example.rickmortymvvm.services.CharacterRepositoryImpl
import com.example.rickmortymvvm.services.CharacterService
import com.google.common.truth.Truth
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

class CharacterRepositoryImplTest {
    lateinit var server: MockWebServer
    lateinit var repository: CharacterRepositoryImpl

    @Before
    fun before() {
        server = MockWebServer()
        server.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CharacterService::class.java)
        repository = CharacterRepositoryImpl(CharacterDataRemoteImpl(service))
    }

    @After
    fun after() {
        server.shutdown()
    }

    @Test
    fun `getListCharacter - get character list successfully`() {
        server.enqueue(
            MockResponse()
                .setBody(
                    readFile("character/character_page_1_test_answer.json")
                )
        )

        val testObserver = repository.getListCharacter()
            .test()

        testObserver.assertResult(expectedResult)
    }

    @SuppressLint("CheckResult") // test only pagination
    @Test
    fun `getListCharacter - When it calls the first time it Should return the first page`() {
        server.enqueue(
            MockResponse()
                .setBody(
                    readFile("character/character_page_1_test_answer.json")
                )
        )

        repository.getListCharacter()
            .test()

        val request = server.takeRequest()
        Truth.assertThat(request.path).isEqualTo("/character/?page=1")
    }

    @Test
    fun `getListCharacter - When returning status code 500 Should return an HttpException`() {
        server.enqueue(MockResponse().setResponseCode(CODE_INTERNAL_SERVER_ERROR))

        val testObserver = repository.getListCharacter()
            .test()

        testObserver.assertFailure(HttpException::class.java)
    }

    @SuppressLint("CheckResult") // test only pagination
    @Test
    fun `getListCharacter - When the request succeeds and request again Should request the next page`() {
        server.enqueue(
            MockResponse()
                .setBody(readFile("character/character_page_1_test_answer.json"))
        )
        server.enqueue(MockResponse().setResponseCode(CODE_INTERNAL_SERVER_ERROR))

        repository.getListCharacter()
            .test()
        repository.getListCharacter()
            .test()

        val path1 = server.takeRequest().path
        Truth.assertThat(path1).isEqualTo("/character/?page=1")

        val path2 = server.takeRequest().path
        Truth.assertThat(path2).isEqualTo("/character/?page=2")
    }

    @SuppressLint("CheckResult") // test only pagination
    @Test
    fun `getListCharacter - When the request error and request again Should request the same page`() {
        server.enqueue(MockResponse().setResponseCode(CODE_INTERNAL_SERVER_ERROR))
        server.enqueue(
            MockResponse()
                .setBody(readFile("character/character_page_1_test_answer.json"))
        )

        repository.getListCharacter()
            .test()
        repository.getListCharacter()
            .test()

        val path1 = server.takeRequest().path
        Truth.assertThat(path1).isEqualTo("/character/?page=1")

        val path2 = server.takeRequest().path
        Truth.assertThat(path2).isEqualTo("/character/?page=1")
    }

    @Test
    fun `getListCharacter - When request after the last page Should complete with empty`() {
        server.enqueue(
            MockResponse()
                .setBody(readFile("character/character_page_1_test_answer_with_a_page.json"))
        )
        server.enqueue(
            MockResponse()
                .setBody(readFile("character/character_page_1_test_answer_with_a_page.json"))
        )

        val testObserverPage1 = repository.getListCharacter()
            .test()
        val testObserverPage2 = repository.getListCharacter()
            .test()

        testObserverPage1.assertResult(expectedResultOnePage)
        testObserverPage2.assertResult()
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
                    "2017-11-04T18:48:46.250Z"
                )
            )
        )
        val expectedResultOnePage = CharacterResponseVO(
            CharacterResponseInfoVO(1),
            listOf(
                Character(
                    1,
                    "Rick Sanchez",
                    "Alive",
                    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    "Human",
                    "",
                    "Male",
                    "2017-11-04T18:48:46.250Z"
                )
            )
        )
        const val CODE_INTERNAL_SERVER_ERROR = 500
    }
}
