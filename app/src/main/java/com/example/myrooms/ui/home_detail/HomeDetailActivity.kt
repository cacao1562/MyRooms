package com.example.myrooms.ui.home_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myrooms.databinding.ActivityHomeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeDetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }
    lateinit var binding: ActivityHomeDetailBinding

    @Inject lateinit var factory: HomeDetailViewModel.HomeDetailViewModelFactory

    private val viewModel: HomeDetailViewModel by viewModels {
        HomeDetailViewModel.provideFactory(factory, intent.getIntExtra(EXTRA_ID, 0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

    }
}