package com.example.todo_client.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val baseUrl = "https://todo-rest-kotlin.herokuapp.com"

    private val okHttpClient = OkHttpClient.Builder();

    private val builder = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(
        okHttpClient.build())

    private val retrofit = builder.build()

    fun<T> buildService (serviceType : Class<T>) : T {
        return retrofit.create(serviceType)
    }
}