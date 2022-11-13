package org.sopt.androidpractice.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/user/signin")
    fun login(
        @Body requestLoginDTO: RequestLoginDto
    ): Call<ResponseLoginDto>
}