package org.sopt.androidpractice.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.sopt.androidpractice.R
import org.sopt.androidpractice.home.adapter.RepoAdapter
import org.sopt.androidpractice.home.data.Repo
import org.sopt.androidpractice.databinding.FragmentHomeBinding

class HomeFragment(): Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "There is error in HomeFragment "}
    private val mockRepoList = listOf<Repo>(
        Repo(
            image = R.drawable.ic_github,
            name = "Manchester is red",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Ronaldo",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Rashford",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Anthony",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "B.Fernandes",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Fred",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Casemiro",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Malacia",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Varane",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "L.Martinez",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "Dalot",
            author = "TaeHee lee"
        ),
        Repo(
            image = R.drawable.ic_github,
            name = "De Gea",
            author = "TaeHee lee"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RepoAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(mockRepoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment{
            return HomeFragment()
        }
    }
}