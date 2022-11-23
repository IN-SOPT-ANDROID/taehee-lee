package org.sopt.androidpractice.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.androidpractice.remote.RequestSignupDto
import org.sopt.androidpractice.remote.ResponseSignupDto
import org.sopt.androidpractice.remote.ServicePool.signupService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<ResponseSignupDto>()
    val signUpResult: LiveData<ResponseSignupDto>
        get() = _signUpResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage //왜 get()을 쓰는지 알아보기 - null값 걸르기 위해?

    fun signUp(email: String, password: String, name: String) {
        signupService.signUp(
            RequestSignupDto(email, password, name)
        ).enqueue(object : Callback<ResponseSignupDto> {
            override fun onResponse(
                call: Call<ResponseSignupDto>,
                response: Response<ResponseSignupDto>
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                } else if (response.code() in 400 until 500) {
                    _errorMessage.value = response.message()
                }
            }

            override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                _errorMessage.value = t.message
            }

        }
        )
    }



}