package org.sopt.androidpractice.home.data

import kotlinx.serialization.SerialName

data class Repo(
    @SerialName("avatar")
    val image:String,
    @SerialName("first-name")
    val name:String,
    @SerialName("email")
    val email:String
)
