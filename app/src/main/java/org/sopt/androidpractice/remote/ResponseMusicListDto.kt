package org.sopt.androidpractice.remote

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMusicListDto(
    val data: List<MusicList>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
) {
    @Serializable
    data class MusicList(
        val id: Int,
        val image: String,
        val singer: String,
        val title: String
    )
}