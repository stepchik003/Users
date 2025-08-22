package com.example.users.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int,
        @Query("inc") inc: String = "name,email,phone,cell,dob,nat,picture,location,id,gender"
    ): UserResponse
}