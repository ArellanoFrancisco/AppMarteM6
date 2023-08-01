package com.example.appmartem6.Model.Remote

import retrofit2.Response
import retrofit2.http.GET

// implementa los verbos de solicitudes a la api1

interface MarsApi {


/*
    @GET("realestate") // vieja confiable
    fun fetchMarsData(): Call<List<MarsRealState>>

*/

    @GET("realestate")
    suspend fun fetchMarsDataCoroutines(): Response<List<MarsRealState>>





}