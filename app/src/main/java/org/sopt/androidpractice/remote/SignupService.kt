package org.sopt.androidpractice.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {
    @POST("api/user/signup")
    fun signUp(
        @Body requestSignupDto: RequestSignupDto
    ) : Call<ResponseSignupDto>
}