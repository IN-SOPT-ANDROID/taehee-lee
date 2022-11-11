package org.sopt.androidpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.ActivitySignUpBinding
import org.sopt.androidpractice.remote.RequestSignupDto
import org.sopt.androidpractice.remote.ServicePool

class SignUpActivity : AppCompatActivity() {
    private var idExist : Boolean = false
    private var passwordExist : Boolean = false
    private var mbtiExist : Boolean = false
    private val isButtonActive get() = idExist && passwordExist && mbtiExist

    private var autoService = ServicePool.autoService

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSignUpEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                idExist = binding.etSignUpEmail.text.isNotBlank()
                binding.btnSignUpComplete.isEnabled = isButtonActive
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignUpPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordExist = binding.etSignUpPassword.text.isNotBlank()
                binding.btnSignUpComplete.isEnabled = isButtonActive
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignUpName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mbtiExist = binding.etSignUpName.text.isNotBlank()
                binding.btnSignUpComplete.isEnabled = isButtonActive
            }

            override fun afterTextChanged(p0: Editable?) {}
        })


        binding.btnSignUpComplete.setOnClickListener {
            if (binding.etSignUpEmail.text.length in 6..10){
                if(binding.etSignUpPassword.text.length in 8..12){
                    autoService.signUp(
                        RequestSignupDto(
                            binding.etSignUpEmail.text.toString(),
                            binding.etSignUpPassword.text.toString(),
                            binding.etSignUpName.text.toString()
                        )
                    )
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("email", binding.etSignUpEmail.text.toString())
                    intent.putExtra("password", binding.etSignUpPassword.text.toString())
                    intent.putExtra("name",binding.etSignUpName.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                else{
                    Snackbar.make(binding.root,"비밀번호가 잘못되었습니다.", Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(binding.root,"아이디가 잘못되었습니다.", Snackbar.LENGTH_LONG).show()
            }
        }

    }

}