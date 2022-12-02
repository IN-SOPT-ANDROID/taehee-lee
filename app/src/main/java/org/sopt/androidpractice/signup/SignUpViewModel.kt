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
        get() = _errorMessage //왜 get()을 쓰는지 알아보기 - null값 걸르기 위해?

    val idText: MutableLiveData<String> = MutableLiveData("")
    val isIdSuit: LiveData<Boolean> = Transformations.map(idText) { isValidIdFormat(it) }

    val pwText: MutableLiveData<String> = MutableLiveData("")
    val isPwSuit: LiveData<Boolean> = Transformations.map(pwText) { isValidPwFormat(it) }

    val nameText: MutableLiveData<String> = MutableLiveData("")
    val isNameSuit: LiveData<Boolean> = Transformations.map(nameText) { isValidNameFormat(it) }

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

    private fun isValidIdFormat(id: String): Boolean {
        if (id == "") {
            return true
        }
        val pattern = Pattern.compile(ID_PATTERN)
        val matcher = pattern.matcher(id)
        return matcher.matches()
    }

    private fun isValidPwFormat(password: String): Boolean {
        if (password == "") {
            return true
        }
        val pattern = Pattern.compile(PW_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun isValidNameFormat(name:String):Boolean{
        return name == ""
    }

    companion object {
        const val ID_PATTERN = """^(?=.*[a-zA-Z])(?=.*\d).{6,10}$"""
        const val PW_PATTERN = """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()?]).{6,12}$"""
    }


    fun setButtonState(): Boolean {
        return (isIdSuit.value == true && isPwSuit.value == true && isNameSuit.value == true &&
                idText.toString().isNotEmpty() && pwText.toString().isNotEmpty()
                && nameText.toString().isNotEmpty())
//        binding.btnSignUpComplete.isEnabled =
//            viewModel.nameText.value!!.isNotBlank() &&
//                    viewModel.isIdSuit.value == true &&
//                    viewModel.isPwSuit.value == true
    }
}