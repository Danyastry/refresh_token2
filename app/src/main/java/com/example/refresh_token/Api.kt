package com.example.refresh_token

import retrofit2.Response
import retrofit2.http.GET


interface Api {

    @GET("/getSomeData")
    suspend fun getSomeData(): Response<ResponseModel>

}