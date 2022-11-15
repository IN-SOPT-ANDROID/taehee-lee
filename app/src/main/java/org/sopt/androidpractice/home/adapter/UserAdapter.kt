package org.sopt.androidpractice.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.androidpractice.databinding.LayoutGithubRepoBinding
import org.sopt.androidpractice.remote.ResponseUserDto

class UserAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<ResponseUserDto.User> = emptyList()

    class UserViewHolder(private val binding: LayoutGithubRepoBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun setUser(user: ResponseUserDto.User) {
            Glide.with(this.binding.root)
                .load(user.avatar)
                .circleCrop()
                .into(binding.imgGithub)
            binding.txtFirstName.text = user.first_name
            binding.txtEmail.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) holder.setUser(userList[position])
    }

    override fun getItemCount() = userList.size

    fun setRepoList(userList: List<ResponseUserDto.User>){
        this.userList = userList.toList()
//        notifyItemChanged(0, userList.size)
        notifyDataSetChanged()
    }

}