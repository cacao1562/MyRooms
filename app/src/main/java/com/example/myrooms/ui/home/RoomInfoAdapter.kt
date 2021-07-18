package com.example.myrooms.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myrooms.databinding.ItemRoomBinding
import com.example.myrooms.loadImageOrDefault
import com.example.myrooms.model.Product
import com.example.myrooms.setToggleHeart
import com.example.myrooms.ui.home_detail.HomeDetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomInfoAdapter(
    private val viewModel: HomeViewModel
): PagingDataAdapter<Product, RoomInfoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomInfoViewHolder {
        return RoomInfoViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: RoomInfoViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.bind(data)
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Product> =
            object : DiffUtil.ItemCallback<Product>() {
                override fun areItemsTheSame(oldItem: Product, newItem: Product) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Product, newItem: Product) =
                    oldItem == newItem
            }
    }

}

class RoomInfoViewHolder(
    private val binding: ItemRoomBinding,
    private val vm: HomeViewModel
):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, HomeDetailActivity::class.java).apply {
                putExtra(HomeDetailActivity.EXTRA_PRODUCT, product)
            }
            itemView.context.startActivity(intent)
        }
        binding.apply {
            tvHomeItemTitle.text = product.name
            tvHomeItemRate.text = product.rate.toString()
            ivHomeItemThumb.loadImageOrDefault(product.thumbnail)
            CoroutineScope(Dispatchers.Main).launch {
                val result = vm.isFavorite(product.id)
                ivHomeItemHeart.isSelected = result
            }
            ivHomeItemHeart.setOnClickListener {
                ivHomeItemHeart.setToggleHeart(product, vm)
            }
        }
    }
    companion object {
        fun from(parent: ViewGroup, viewModel: HomeViewModel): RoomInfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRoomBinding.inflate(layoutInflater, parent, false)
            return RoomInfoViewHolder(binding, viewModel)
        }
    }
}