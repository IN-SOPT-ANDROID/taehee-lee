package org.sopt.androidpractice.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.androidpractice.databinding.ActivityLoginBinding
import org.sopt.androidpractice.home.HomeActivity
import org.sopt.androidpractice.remote.RequestLoginDto
import org.sopt.androidpractice.remote.ResponseLoginDto
import org.sopt.androidpractice.remote.ServicePool
import org.sopt.androidpractice.signup.SignUpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_LONG).show()
                }
            }

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etId.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        viewModel.loginResult.observe(this) {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, "$it error", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }

    }

}

