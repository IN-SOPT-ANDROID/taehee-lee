package org.sopt.androidpractice.home

import androidx.lifecycle.ViewModel
import org.sopt.androidpractice.remote.ResponseUserDto

class UserViewModel : ViewModel() {
    val userList = mutableListOf<ResponseUserDto.User>()
}