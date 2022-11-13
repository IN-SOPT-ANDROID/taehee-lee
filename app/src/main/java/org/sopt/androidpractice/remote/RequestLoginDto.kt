package org.sopt.androidpractice.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto (
    @SerialName("email")
    val email:String,
    @SerialName("password")
    val password:String
)