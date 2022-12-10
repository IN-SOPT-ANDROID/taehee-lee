package org.sopt.androidpractice.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.androidpractice.remote.MusicServicePool
import org.sopt.androidpractice.remote.ResponseMusicListDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel : ViewModel() {
    private val _music = MutableLiveData<ResponseMusicListDto>()
    val music: LiveData<ResponseMusicListDto>
        get() = _music

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val musicList = mutableListOf<ResponseMusicListDto.MusicList>()
//    private val playListService = MusicServicePool.musicService
//
//    fun generatePlayList() {
//        playListService.getPlaylist().enqueue(object : Callback<ResponseMusicListDto> {
//            override fun onResponse(
//                call: Call<ResponseMusicListDto>,
//                response: Response<ResponseMusicListDto>
//            ) {
//                if (response.isSuccessful) {
//                    _music.value = response.body()!!
//                } else if (response.code() in 400 until 500) {
//                    _errorMessage.value = response.message()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseMusicListDto>, t: Throwable) {
//                _errorMessage.value = t.message
//            }
//        })
//
//    }
}