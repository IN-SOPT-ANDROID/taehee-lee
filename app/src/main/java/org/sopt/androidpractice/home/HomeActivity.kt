package org.sopt.androidpractice.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import org.sopt.androidpractice.R
import org.sopt.androidpractice.databinding.ActivityHomeBinding
import org.sopt.androidpractice.gallery.GalleryFragment
import org.sopt.androidpractice.search.SearchFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentFragment = supportFragmentManager.findFragmentById(R.id.home_container)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.home_container, HomeFragment())
                .commit()

        }


        binding.bnvHome.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home_menu -> {
                    changeFragment(HomeFragment())
                }
                R.id.home_gallery -> {
                    changeFragment(GalleryFragment())
                }
                R.id.home_search -> {
                    changeFragment(SearchFragment())
                }
                else -> error("item id: {${it.itemId} is cannot be selected")
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, fragment)
            .commit()

    }
}