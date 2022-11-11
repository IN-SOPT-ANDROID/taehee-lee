package org.sopt.androidpractice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.ActivityLoginBinding
import org.sopt.androidpractice.home.HomeActivity
import org.sopt.androidpractice.remote.RequestLoginDto
import org.sopt.androidpractice.remote.ResponseLoginDto
import org.sopt.androidpractice.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var autoService = ServicePool.autoService

    private var id: String? = null
    private var password: String? = null
    private var mbti: String? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_LONG).show()
                    id = result.data?.getStringExtra("email") ?: ""
                    password = result.data?.getStringExtra("password") ?: ""
                    mbti = result.data?.getStringExtra("name") ?: ""
                } 
            }

        binding.btnLogin.setOnClickListener {
            if (binding.etId.text.toString() == id) {
                if (binding.etPassword.text.toString() != password.toString()) {
                    Snackbar.make(binding.root, "패스워드가 잘못 되었습니다.", Snackbar.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this, HomeActivity::class.java)
                    autoService.login(
                        RequestLoginDto(
                            binding.etId.text.toString(),
                            binding.etPassword.text.toString()
                        )
                    ).enqueue(object : Callback<ResponseLoginDto> {
                        override fun onResponse(
                            call: Call<ResponseLoginDto>,
                            response: Response<ResponseLoginDto>
                        ) {
                            if (response.isSuccessful) {
                                intent.putExtra("mbti", mbti)
                                startActivity(intent)
                                finish()
                            } else if (response.code() == 404) {

                            } else if (response.code() == 401) {

                            }
                        }

                        override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                            Snackbar.make(binding.root, "서버 통신 장애가 발생", Snackbar.LENGTH_LONG).show()
                        }
                    }
                    )


                }
            } else {
                Snackbar.make(binding.root, "아이디가 잘못 되었습니다.", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }
}
