package com.example.randomuser.data.api

import com.example.randomuser.data.dto.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    suspend fun getRandomUser(
        @Query("gender") gender: String,
        @Query("nat") nationality: String,
    ): RandomUserResponse

}