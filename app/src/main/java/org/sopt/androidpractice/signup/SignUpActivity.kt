package org.sopt.androidpractice.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import org.sopt.androidpractice.R
import org.sopt.androidpractice.databinding.ActivitySignUpBinding
import org.sopt.androidpractice.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private val viewModel by viewModels<SignUpViewModel>() //위임 공부해보기

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        observeSignUpFormat()
        signUp()

    }

    private fun observeSignUpFormat() {
        viewModel.isIdSuit.observe(this) {
            if (it==true){
                binding.layoutEtId.error = null
                setButtonClickable()
            }
            else{
                binding.layoutEtId.error = "아이디 형식이 올바르지 않습니다."
            }
        }

        viewModel.isPwSuit.observe(this) {
            if (it==true){
                binding.layoutEtPw.error = null
                setButtonClickable()
            }
            else{
                binding.layoutEtPw.error = "비밀번호 형식이 올바르지 않습니다."
            }
        }

        viewModel.isNameSuit.observe(this){
            if (it==true){
                binding.layoutEtName.error = null
                setButtonClickable()
            }
            else{
                binding.layoutEtName.error = "이름에 공백이 있습니다."
            }
        }
    }

    private fun setButtonClickable(){
        if(viewModel.setButtonState()){
            binding.btnSignUpComplete.setBackgroundColor(getColor(R.color.teal_200))
            binding.btnSignUpComplete.isClickable = true
        }
        else{
            binding.btnSignUpComplete.setBackgroundColor(getColor(R.color.gray))
            binding.btnSignUpComplete.isClickable = false
        }
    }


    private fun signUp() {
        binding.btnSignUpComplete.setOnClickListener {
            viewModel.signUp(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
        }

        viewModel.signUpResult.observe(this) {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, "$it error", Snackbar.LENGTH_SHORT).show()
        }
    }

}