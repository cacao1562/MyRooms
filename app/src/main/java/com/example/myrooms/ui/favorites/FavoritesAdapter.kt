package com.example.myrooms.ui.favorites

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myrooms.databinding.ItemFavoritesBinding
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.ui.home_detail.HomeDetailActivity

class FavoritesAdapter(
    private val viewModel: FavoritesViewModel
): ListAdapter<RoomEntity, FavoritesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.bind(viewModel, data)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<RoomEntity> =
            object : DiffUtil.ItemCallback<RoomEntity>() {
                override fun areItemsTheSame(oldItem: RoomEntity, newItem: RoomEntity) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: RoomEntity, newItem: RoomEntity) =
                    oldItem == newItem
            }
    }

}

class FavoritesViewHolder(private val binding: ItemFavoritesBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: FavoritesViewModel, roomEntity: RoomEntity) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, HomeDetailActivity::class.java).apply {
                putExtra(HomeDetailActivity.EXTRA_ID, roomEntity.id)
            }
            itemView.context.startActivity(intent)
        }
        binding.data = roomEntity
        binding.viewModel = viewModel
    }

    companion object {
        fun from(parent: ViewGroup): FavoritesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemFavoritesBinding.inflate(layoutInflater, parent, false)
            return FavoritesViewHolder(binding)
        }
    }
}