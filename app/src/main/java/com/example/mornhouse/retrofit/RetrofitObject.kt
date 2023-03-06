package com.example.mornhouse.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    val api: NumbersApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("http://numbersapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumbersApiInterface::class.java)
    }
}