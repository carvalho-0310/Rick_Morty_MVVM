package com.example.rickmortymvvm.list.services

import com.example.rickmortymvvm.list.services.models.CharacterResponseVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    fun listCharacter(@Query("page") currentPage: Int): Call<CharacterResponseVO?>?

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}