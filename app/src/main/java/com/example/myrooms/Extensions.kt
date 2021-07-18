package com.example.myrooms

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.model.Product
import com.example.myrooms.ui.BaseViewModel
import java.text.DecimalFormat
import java.util.*

fun ImageView.loadImageOrDefault(imgUrl: String) {
    if (imgUrl.isNotEmpty())
        Glide.with(context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.stat_notify_error))
            .into(this)
    else
        this.setImageResource(android.R.drawable.star_off)
}

fun Int.formatToPrice(): String {
    return "￦ ${DecimalFormat("#,###").format(this)}"
}

fun ImageView.setToggleHeart(product: Product, viewModel: BaseViewModel) {
    if (this.isSelected) {
        viewModel.deleteRoomById(product.id)
    }else {
        val data = RoomEntity(
            product.id,
            product.name,
            product.thumbnail,
            product.description.imagePath,
            product.description.subject,
            product.description.price,
            product.rate,
            Calendar.getInstance().time
        )
        viewModel.insertRoom(data)
    }
    this.isSelected = !this.isSelected
}