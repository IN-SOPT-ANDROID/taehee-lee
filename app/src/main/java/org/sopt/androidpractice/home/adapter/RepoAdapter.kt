package org.sopt.androidpractice.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidpractice.home.data.Repo
import org.sopt.androidpractice.databinding.LayoutGithubRepoBinding
import org.sopt.androidpractice.databinding.LayoutTitleBinding

class RepoAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<Repo> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TITLE_ID
            else -> REPO_ID
        }
    }

    class TitleViewHolder(
        private var binding: LayoutTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Repo){
            binding.txtRvTitle.text = data.name
        }
    }

    class RepoViewHolder(
        private var binding: LayoutGithubRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Repo) {
            binding.imgGithub.setImageResource(data.image) //drawable 차이 알아보기
            binding.txtGithubRepoName.text = data.name
            binding.txtGithubRepoAuthor.text = data.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_ID -> {
                TitleViewHolder(
                    LayoutTitleBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
            else -> {
                RepoViewHolder(
                    LayoutGithubRepoBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepoViewHolder -> holder.onBind(repoList[position])
            is TitleViewHolder -> holder.onBind(repoList[position])
            else -> throw IllegalArgumentException("holder : $holder")
        }
    }

    override fun getItemCount() = repoList.size

    fun setRepoList(repoList: List<Repo>) {
        this.repoList = repoList.toList()
        notifyDataSetChanged()
    }

    companion object {
        private const val TITLE_ID = 0
        private const val REPO_ID = 1
    }

}