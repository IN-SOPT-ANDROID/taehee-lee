package org.sopt.androidpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUpComplete.setOnClickListener {
            if (binding.etSignUpId.text.length in 6..10){
                if(binding.etSignUpPassword.text.length in 8..12){
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("id", binding.etSignUpId.text.toString())
                    intent.putExtra("password", binding.etSignUpPassword.text.toString())
                    intent.putExtra("mbti",binding.etMbti.text.toString())
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