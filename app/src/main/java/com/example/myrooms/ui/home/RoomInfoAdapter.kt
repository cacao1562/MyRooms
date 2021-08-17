package com.example.myrooms.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myrooms.databinding.ItemRoomBinding
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.ui.home_detail.HomeDetailActivity

class RoomInfoAdapter(
    private val viewModel: HomeDataViewModel
): PagingDataAdapter<RoomEntity, RoomInfoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomInfoViewHolder {
        return RoomInfoViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: RoomInfoViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.bind(data)
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

class RoomInfoViewHolder(
    private val binding: ItemRoomBinding,
    private val vm: HomeDataViewModel
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: RoomEntity) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, HomeDetailActivity::class.java).apply {
                putExtra(HomeDetailActivity.EXTRA_ID, data.id)
            }
            itemView.context.startActivity(intent)
        }
        binding.data = data
        binding.viewModel = vm

    }
    companion object {
        fun from(parent: ViewGroup, viewModel: HomeDataViewModel): RoomInfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRoomBinding.inflate(layoutInflater, parent, false)
            return RoomInfoViewHolder(binding, viewModel)
        }
    }
}