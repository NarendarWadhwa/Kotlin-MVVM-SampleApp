package com.example.findme.network

import com.example.findme.network.response.BaseResponse
import com.example.findme.network.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {

    @GET("users")
    fun fetchUsers(): Single<List<UserResponse>>
}