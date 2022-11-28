package org.sopt.androidpractice.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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

        binding.etSignUpId.setText(viewModel.idText.value)
        binding.etSignUpPassword.setText(viewModel.pwText.value)
        binding.etSignUpName.setText(viewModel.nameText.value)

        btnActive()
        signUp()

    }


    private fun checkAllEditText() {
        binding.btnSignUpComplete.isEnabled =
                binding.etSignUpId.text.toString().isNotBlank() &&
                binding.etSignUpPassword.text.toString().isNotBlank() &&
                binding.etSignUpName.text.toString().isNotBlank() &&
                viewModel.isIdSuit.value == true && viewModel.isPwSuit.value == true
    }

    private fun btnActive() {
        viewModel.idText.observe(this){checkAllEditText()}
        viewModel.pwText.observe(this){checkAllEditText()}
        viewModel.nameText.observe(this){checkAllEditText()}
    }


    private fun signUp() {
        binding.btnSignUpComplete.setOnClickListener {
            viewModel.signUp(
                binding.etSignUpId.text.toString(),
                binding.etSignUpPassword.text.toString(),
                binding.etSignUpName.text.toString()
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