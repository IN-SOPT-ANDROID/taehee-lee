package org.sopt.androidpractice.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit



object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }
    val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    val interceptor = Interceptor{ chain ->
        val originalRequest = chain.request()
        // 서버에서 발급 받은 토큰을 응답값에 저장하는 로직이 있어야 함
        // 그래야 SharedPreference에서 토큰 값을 가져와서 여기에다 넣어줘야지
        // newBuilder는 기존 request 기반으로 새로운 parameter를 추가해서 새로운 request를 만들어내는 것이다
        val headerRequest = originalRequest.newBuilder()
            .header("token", "nunu")
            .build()
        // proceed를 통해서 서버통신 응답값을 받아올 수 있고 우리는 응답값에 어떠한 커스텀도 안하기에
        // 일단 내려보낸다
        chain.proceed(headerRequest)
    }
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java) //서버통신 해주는 친구

}

object ServicePool{
    val loginService = ApiFactory.create<LoginService>()
    val signupService = ApiFactory.create<SignupService>()
}