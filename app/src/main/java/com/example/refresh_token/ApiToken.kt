package com.example.refresh_token

import retrofit2.Call
import retrofit2.http.POST

interface ApiToken {

    @POST("/createToken")
    fun createToken(): Call<ResponseModel>

}