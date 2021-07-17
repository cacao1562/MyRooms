package com.example.myrooms.ui.home_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myrooms.databinding.ActivityHomeDetailBinding
import com.example.myrooms.formatToPrice
import com.example.myrooms.loadImageOrDefault
import com.example.myrooms.model.Product

class HomeDetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
    }
    lateinit var binding: ActivityHomeDetailBinding

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
        }
    }
}