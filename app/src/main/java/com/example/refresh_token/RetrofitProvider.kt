package com.example.refresh_token

import android.content.SharedPreferences
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    const val BASE_URL = ""
    const val JWT_TOKEN = ""

    private fun getRetrofit(
        sharedPreferences: SharedPreferences
    ): Retrofit {

        val authInterceptor = AuthInterceptor(sharedPreferences = sharedPreferences)

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getApi(sharedPreferences: SharedPreferences): Api =
        getRetrofit(sharedPreferences = sharedPreferences).create(Api::class.java)

    fun getTokenApi(): ApiToken {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(ApiToken::class.java)
    }
}