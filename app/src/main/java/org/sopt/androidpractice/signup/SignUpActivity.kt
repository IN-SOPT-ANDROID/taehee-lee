package org.sopt.androidpractice.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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
            Log.e("observingSignUp", it.toString())
            setButtonState()
        }

        viewModel.isPwSuit.observe(this) {
            Log.e("observingPW",it.toString())
            setButtonState()
        }
    }

    private fun setButtonState() {
        binding.btnSignUpComplete.isEnabled =
            viewModel.nameText.value!!.isNotBlank() &&
                    viewModel.isIdSuit.value == true &&
                    viewModel.isPwSuit.value == true
    }


    private fun signUp() {
        binding.btnSignUpComplete.setOnClickListener {
            viewModel.signUp(
                binding.etSignUpId.text.toString(),
                binding.etSignUpPassword.text.toString(),
                binding.etSignUpName.text.toString()
//                viewModel.idText.value.toString(),
//                viewModel.pwText.value.toString(),
//                viewModel.nameText.value.toString()
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