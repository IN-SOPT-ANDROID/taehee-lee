package org.sopt.androidpractice.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.androidpractice.remote.RequestSignupDto
import org.sopt.androidpractice.remote.ResponseSignupDto
import org.sopt.androidpractice.remote.ServicePool.signupService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<ResponseSignupDto>()
    val signUpResult: LiveData<ResponseSignupDto>
        get() = _signUpResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage //왜 get()을 쓰는지 알아보기 - null값 걸르기 위해? 아닙니다 태희쿤

    val idText: MutableLiveData<String> = MutableLiveData("")
    val isIdSuit: LiveData<Boolean> = Transformations.map(idText) {
        isValidIdFormat(it)
    }

    val pwText: MutableLiveData<String> = MutableLiveData("")
    val isPwSuit: LiveData<Boolean> = Transformations.map(pwText) { isValidPwFormat(it) }

    val nameText: MutableLiveData<String> = MutableLiveData("")

    fun signUp(id: String, password: String, name: String) {
        signupService.signUp(
            RequestSignupDto(id, password, name)
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

    // 함수는 동사형으로 써야한답니다 태희쿤
    private fun isValidIdFormat(id: String): Boolean {
        if (id.isBlank()) {
            return false
        }
        val pattern = Pattern.compile(ID_PATTERN)
        val matcher = pattern.matcher(id)
        return matcher.matches()
    }

    // 함수는 동사형으로 써야한답니다 태희쿤
    private fun isValidPwFormat(password: String): Boolean {
        if (password.isBlank()) {
            return false
        }
        val pattern = Pattern.compile(PW_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    // 상수화해주는게 좋겠죠??
    companion object {
        const val PW_PATTERN = """^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[$@$!%*#?&]).{6,12}$"""
        const val ID_PATTERN = """^(?=.*[0-9])(?=.*[a-zA-Z]).{6,10}$"""
    }
}