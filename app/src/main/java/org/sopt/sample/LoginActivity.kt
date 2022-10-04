package org.sopt.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var id:String? = null
    private var password:String? = null
    private var mbti:String? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result -> if(result.resultCode == Activity.RESULT_OK){
            id = result.data?.getStringExtra("id") ?: ""
            password = result.data?.getStringExtra("password") ?: ""
            mbti = result.data?.getStringExtra("mbti") ?: ""
        }
        }

        binding.btnLogin.setOnClickListener{
            if(binding.etId.text.toString()==id){
                if(binding.etPassword.text.toString()!=password.toString()){
                    Snackbar.make(binding.root,"패스워드가 잘못 되었습니다.", Snackbar.LENGTH_LONG).show()
                }else{
                    Snackbar.make(binding.root,"로그인에 성공하셨습니다.", Snackbar.LENGTH_LONG ).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("mbti",mbti)
                    startActivity(intent)
                    finish()
                }
            }else{
                Snackbar.make(binding.root,"아이디가 잘못 되었습니다.", Snackbar.LENGTH_LONG ).show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }
}
