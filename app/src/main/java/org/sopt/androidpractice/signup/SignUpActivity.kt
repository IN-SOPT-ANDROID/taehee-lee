package org.sopt.androidpractice.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.LoginActivity
import org.sopt.androidpractice.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private var emailExist: Boolean = false
    private var passwordExist: Boolean = false
    private var nameExist: Boolean = false
    private val isButtonActive get() = emailExist && passwordExist && nameExist

    private val viewModel by viewModels<SignUpViewModel>() //위임 공부해보기

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSignUpEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailExist = binding.etSignUpEmail.text.isNotBlank()
                binding.btnSignUpComplete.isEnabled = isButtonActive
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignUpPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordExist = binding.etSignUpPassword.text.isNotBlank()
                binding.btnSignUpComplete.isEnabled = isButtonActive
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignUpName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameExist = binding.etSignUpName.text.isNotBlank()
                binding.btnSignUpComplete.isEnabled = isButtonActive
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnSignUpComplete.setOnClickListener {
            viewModel.signUp(
                binding.etSignUpEmail.text.toString(),
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