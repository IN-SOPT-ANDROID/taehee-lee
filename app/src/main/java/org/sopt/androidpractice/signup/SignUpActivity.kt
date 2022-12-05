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
        initSignUpButton()
        observeSignUpFormat()
        observeSignUpResult()
        observeErrorEvent()
//        observeButtonState()
    }

    private fun observeSignUpFormat() {
        // mediatorLiveData
        viewModel.isIdSuit.observe(this) { isIdSuit ->
            binding.layoutEtId.error = if (isIdSuit) "" else "아이디 형식이 올바르지 않습니다."
            viewModel.setButtonState()
        }

        viewModel.isPwSuit.observe(this) { isPwSuit ->
            binding.layoutEtPw.error = if (isPwSuit) "" else "비밀번호 형식이 올바르지 않습니다."
            viewModel.setButtonState()
        }

        viewModel.isNameSuit.observe(this) { isNameSuit ->
            binding.layoutEtName.error = if (isNameSuit) "" else "이름에 공백이 있습니다."
            viewModel.setButtonState()
        }
    }

//    private fun observeButtonState() {
//        viewModel.isButtonActive.observe(this) { buttonState ->
//            binding.btnSignUpComplete.isEnabled = buttonState
//        }
//    }

    private fun observeSignUpResult() {
        viewModel.signUpResult.observe(this) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun observeErrorEvent() {
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, "$it error", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initSignUpButton() {
        binding.btnSignUpComplete.setOnClickListener {
            viewModel.signUp(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
        }
    }
}