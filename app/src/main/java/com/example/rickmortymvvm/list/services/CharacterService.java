package com.example.rickmortymvvm.list.services;

import com.example.rickmortymvvm.list.services.models.CharacterResponseVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterService {
    String BASE_URL = "https://rickandmortyapi.com/api/";

    @GET("character/")
    Call<CharacterResponseVO> listCharacter(@Query("page") int currentPage);
}

