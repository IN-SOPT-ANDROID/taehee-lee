package org.sopt.androidpractice.home.data

import androidx.annotation.DrawableRes

data class Repo(
    @DrawableRes val image: Int,
    val name:String,
    val author:String
)
