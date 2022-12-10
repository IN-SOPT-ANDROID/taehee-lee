package org.sopt.androidpractice.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.ActivityPlayListBinding
import org.sopt.androidpractice.home.adapter.UserAdapter
import org.sopt.androidpractice.remote.MusicServicePool
import org.sopt.androidpractice.remote.ResponseMusicListDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListActivity : AppCompatActivity() {
    private val playListService = MusicServicePool.musicService
    private val viewModel : PlayListViewModel by viewModels()

    private var _binding: ActivityPlayListBinding? = null
    val binding : ActivityPlayListBinding
        get() = requireNotNull(_binding) {"error in MusicActivity"}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MusicAdapter(this)
        playListService.getPlaylist().enqueue(object : Callback<ResponseMusicListDto>{
            override fun onResponse(
                call: Call<ResponseMusicListDto>,
                response: Response<ResponseMusicListDto>
            ) {
                if(response.isSuccessful){
                    viewModel.musicList.addAll(response.body()?.data!!)

                    binding.rvMusic.adapter = adapter
                    adapter.setPlayList(viewModel.musicList)
                }
                else if (response.code() in 400 until 500) {
                    Snackbar.make(binding.root, "{${response.code()}error}", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseMusicListDto>, t: Throwable) {
                Snackbar.make(binding.root, "서버 통신 장애가 발생", Snackbar.LENGTH_LONG).show()
            }
        })


    }


}