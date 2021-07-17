package com.example.myrooms

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.DecimalFormat

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