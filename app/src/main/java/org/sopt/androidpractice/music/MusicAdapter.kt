package org.sopt.androidpractice.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.androidpractice.databinding.ItemMusicBinding
import org.sopt.androidpractice.remote.ResponseMusicListDto

class MusicAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var playList: List<ResponseMusicListDto.MusicList> = emptyList()

    class MusicViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setPlayList(music: ResponseMusicListDto.MusicList) {
            Glide.with(this.binding.root)
                .load(music.image)
                .circleCrop()
                .into(binding.music)
            binding.musicTitle.text = music.title
            binding.singer.text = music.singer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMusicBinding.inflate(inflater, parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MusicViewHolder) holder.setPlayList(playList[position])
    }

    override fun getItemCount() = playList.size

    fun setPlayList(playList: List<ResponseMusicListDto.MusicList>) {
        this.playList = playList.toList()
        notifyDataSetChanged()
    }
}