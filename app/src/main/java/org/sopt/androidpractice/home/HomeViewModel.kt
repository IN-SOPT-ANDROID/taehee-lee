package org.sopt.androidpractice.home

import androidx.lifecycle.ViewModel
import org.sopt.androidpractice.R
import org.sopt.androidpractice.home.data.Repo

class HomeViewModel: ViewModel() {
    //생성 예정
    val mockRepoList = listOf<Repo>(
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
}