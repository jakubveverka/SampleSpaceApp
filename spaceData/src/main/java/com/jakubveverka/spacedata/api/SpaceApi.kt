package com.jakubveverka.spacedata.api

import com.jakubveverka.spacedata.api.model.LaunchDto
import retrofit2.Call
import retrofit2.http.GET

interface SpaceApi {

    @GET("v5/launches/past")
    fun getLaunches(): Call<List<LaunchDto>>

}