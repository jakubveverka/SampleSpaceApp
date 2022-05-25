package com.jakubveverka.spacedata.api

import retrofit2.Call
import retrofit2.http.GET

interface SpaceApi {

    @GET("v5/launches/past")
    fun getLaunches(): Call<List<Launch>>

}