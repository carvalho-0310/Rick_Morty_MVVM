package com.example.rickmortymvvm.services

import com.example.rickmortymvvm.services.models.CharacterResponseVO
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    fun listCharacter(@Query("page") currentPage: Int): Observable<CharacterResponseVO?>

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}
