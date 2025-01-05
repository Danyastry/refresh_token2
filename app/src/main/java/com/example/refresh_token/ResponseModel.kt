package com.example.refresh_token

data class ResponseModel(
    val data: String? = null,
    val token: String? = null,
    val message: String? = null,
    val isLoading: Boolean? = null
)
