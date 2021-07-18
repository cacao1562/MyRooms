package com.example.myrooms.ui.home_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myrooms.databinding.ActivityHomeDetailBinding
import com.example.myrooms.formatToPrice
import com.example.myrooms.loadImageOrDefault
import com.example.myrooms.model.Product
import com.example.myrooms.setToggleHeart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
    }
    lateinit var binding: ActivityHomeDetailBinding
    private val viewModel: HomeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        product?.let {
            binding.ivHomeDetailImage.loadImageOrDefault(it.description.imagePath)
            binding.tvHomeDetailTitle.text = it.name
            binding.tvHomeDetailSubject.text = it.description.subject
            binding.tvHomeDetailRate.text = it.rate.toString()
            binding.tvHomeItemPrice.text = it.description.price.formatToPrice()
            binding.ivHomeDetailHeart.setOnClickListener {
                binding.ivHomeDetailHeart.setToggleHeart(product, viewModel)
            }
            viewModel.getRoomById(product.id)
        }

        binding.ivHomeDetailHeart.isSelected = false

        viewModel.roomEntity.observe(this, Observer { roomEntity ->
            roomEntity?.let {
                binding.ivHomeDetailHeart.isSelected = true
            }
        })

    }
}