package org.sopt.androidpractice.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    @SerialName("email")
    val email : String,
    @SerialName("password")
    val password : String,
    @SerialName("name")
    val name : String,
)
