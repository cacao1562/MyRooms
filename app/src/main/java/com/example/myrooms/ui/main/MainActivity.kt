package com.example.myrooms.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.myrooms.R
import com.example.myrooms.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.mainViewpager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewpager.registerOnPageChangeCallback(viewpagerCallback)

        binding.bnMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page_home -> {
                    binding.mainViewpager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.page_favorites -> {
                    binding.mainViewpager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private val viewpagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            binding.bnMain.selectedItemId = when(position) {
                0 -> R.id.page_home
                1 -> R.id.page_favorites
                else -> error("No id")
            }
        }
    }
}

