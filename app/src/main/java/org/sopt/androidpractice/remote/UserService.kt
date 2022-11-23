package org.sopt.androidpractice.remote

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("api/users")
    fun getUser(): Call<ResponseUserDto>
}