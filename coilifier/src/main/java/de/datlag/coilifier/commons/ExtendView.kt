package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap

internal fun View.getBitmap(): Bitmap? {
    fun screenshotView(): Bitmap? {
        val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        this.draw(canvas)
        return bitmap
    }
    return if (this.width > 0 && this.height > 0) {
        return if (this is ImageView) {
            return try {
                this.drawable?.toBitmap() ?: screenshotView()
            } catch (ignored: Exception) {
                screenshotView()
            }
        } else {
            screenshotView()
        }
    } else {
        null
    }
}
