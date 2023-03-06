package com.example.mornhouse.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiInterface {

    @GET("/random?json")
    suspend fun getRandomNumber():Response<NumberDataClass>

    @GET("/{number}?json")
    suspend fun getNumber(@Path("number")number: String):Response<NumberDataClass>


}