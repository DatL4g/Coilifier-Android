package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

internal fun Bitmap.scale(wantedSize: Int, byWidth: Boolean): Bitmap {
    val scaleWidthFactor: Float = if (byWidth) this.width.toFloat() / this.width.toFloat() else this.width.toFloat() / this.height.toFloat()
    val scaleHeightFactor: Float = if (byWidth) this.height.toFloat() / this.width.toFloat() else this.height.toFloat() / this.height.toFloat()
    val scaledWidth = (scaleWidthFactor * wantedSize).toInt()
    val scaledHeight = (scaleHeightFactor * wantedSize).toInt()
    return try {
        val bitmap = Bitmap.createScaledBitmap(this, scaledWidth, scaledHeight, true)
        if (bitmap.isValid()) {
            bitmap
        } else {
            this
        }
    } catch (ignored: Exception) {
        this
    }
}

internal fun Bitmap?.isValid(): Boolean {
    return this != null && !this.isRecycled && this.height > 0 && this.width > 0
}

internal fun Drawable?.isValid(): Boolean {
    return this != null && this.intrinsicHeight > 0 && this.intrinsicWidth > 0
}