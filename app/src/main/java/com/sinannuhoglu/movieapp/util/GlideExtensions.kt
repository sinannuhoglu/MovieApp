package com.sinannuhoglu.movieapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.sinannuhoglu.movieapp.R

fun ImageView.loadRoundedImage(path: String?) {
    val cornerRadiusInPx = 20

    Glide.with(this.context)
        .load(Constants.IMAGE_BASE_URL + path)
        .apply(
            centerCropTransform()
                .error(R.drawable.ic_error)
                .transform(RoundedCorners(cornerRadiusInPx))
        )
        .into(this)
}

fun ImageView.loadImage(path: String?) {
    Glide.with(this.context)
        .load(Constants.IMAGE_BASE_URL + path)
        .apply(
            centerCropTransform()
                .error(R.drawable.ic_error)
        )
        .into(this)

}

fun ImageView.loadCircleImage(path: String?) {
    Glide.with(this.context)
        .load(Constants.IMAGE_BASE_URL + path)
        .apply(
            com.bumptech.glide.request.RequestOptions.circleCropTransform()
                .error(R.drawable.ic_error)
        )
        .into(this)
}