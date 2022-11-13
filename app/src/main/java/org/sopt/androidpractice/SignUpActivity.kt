package org.sopt.androidpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.ActivitySignUpBinding
import org.sopt.androidpractice.remote.RequestSignupDto
import org.sopt.androidpractice.remote.ResponseSignupDto
import org.sopt.androidpractice.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private var emailExist: Boolean = false
    private var passwordExist: Boolean = false
    private var nameExist: Boolean = false
    private val isButtonActive get() = emailExist && passwordExist && nameExist

    private var signupService = ServicePool.signupService

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
            signupService.signUp(
                RequestSignupDto(
                    binding.etSignUpEmail.text.toString(),
                    binding.etSignUpPassword.text.toString(),
                    binding.etSignUpName.text.toString()
                )
            ).enqueue(object : Callback<ResponseSignupDto> {
                override fun onResponse(
                    call: Call<ResponseSignupDto>,
                    response: Response<ResponseSignupDto>
                ) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    } else if (response.code() == 404) {
                        Snackbar.make(binding.root, "404 error", Snackbar.LENGTH_LONG)
                            .show()
                    } else if (response.code() == 401) {
                        Snackbar.make(binding.root, "401 error", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                    Snackbar.make(binding.root, "서버 통신 장애가 발생", Snackbar.LENGTH_LONG).show()
                }

            }
            )


        }

    }

}