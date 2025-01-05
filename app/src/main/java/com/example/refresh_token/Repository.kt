package com.example.refresh_token

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow

class Repository(
    private val sharedPreferences: SharedPreferences
) {
    fun getSomeData() = flow {
        emit(ResponseModel(isLoading = true))
        val response =
            RetrofitProvider.getApi(sharedPreferences = sharedPreferences).getSomeData()
        if (response.isSuccessful) {
            emit(response.body()?.copy(isLoading = false))
        } else {
            val responseModel =
                Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
            emit(responseModel?.copy(isLoading = false))
        }
    }
}