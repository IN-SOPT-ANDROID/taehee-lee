package org.sopt.androidpractice.remote

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("music/list")
    fun getPlaylist(): Call<ResponseMusicListDto>
}