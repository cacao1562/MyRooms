package com.example.myrooms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myrooms.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvResponse.movementMethod = ScrollingMovementMethod()

        binding.btnFetch.setOnClickListener {
            viewModel.fetchRooms()
        }

        viewModel.roomInfo.observe(this, Observer { data ->
            binding.tvResponse.text = data.toString()
        })
    }
}