package org.sopt.androidpractice.login

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.home.HomeActivity
import org.sopt.androidpractice.remote.RequestLoginDto
import org.sopt.androidpractice.remote.ResponseLoginDto
import org.sopt.androidpractice.remote.ResponseSignupDto
import org.sopt.androidpractice.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult

    private val loginService = ServicePool.loginService

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun login(email: String, password: String) {
        loginService.login(
            RequestLoginDto(
                email,
                password
            )
        ).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>
            ) {
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                } else if (response.code() in 400 until 500) {
                    _errorMessage.value = response.message()
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                _errorMessage.value = t.message
            }
        })
    }
}