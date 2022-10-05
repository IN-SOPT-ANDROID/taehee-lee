package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Snackbar.make(binding.root,"로그인에 성공하셨습니다.", Snackbar.LENGTH_LONG ).show()
        var user_mbti = intent.getStringExtra("mbti")
        binding.txtMbti.text = "MBTI: $user_mbti"

    }
}