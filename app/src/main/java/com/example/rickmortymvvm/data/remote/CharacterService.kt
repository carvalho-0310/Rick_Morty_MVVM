package com.example.rickmortymvvm.data.repository.remote

import com.example.rickmortymvvm.data.models.CharacterResponseVO
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    fun listCharacter(@Query("page") currentPage: Int): Observable<CharacterResponseVO?>
}
