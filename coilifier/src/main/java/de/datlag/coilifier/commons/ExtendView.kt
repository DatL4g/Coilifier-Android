package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

internal fun View.getBitmap(): Bitmap? {
    return if (this.width > 0 && this.height > 0) {
        val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        this.draw(canvas)
        bitmap
    } else {
        null
    }
}
