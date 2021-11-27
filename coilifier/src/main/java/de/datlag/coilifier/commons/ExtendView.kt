package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
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

internal val View.blurWidth: Int?
    get() {
        return when {
            this.width > 0 -> this.width
            this.measuredWidth > 0 -> this.measuredWidth
            this.minimumWidth > 0 -> this.minimumWidth
            this.layoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT -> resources?.displayMetrics?.widthPixels
            else -> null
        }
    }

internal val View.blurHeight: Int?
    get() {
        return when {
            this.height > 0 -> this.height
            this.measuredHeight > 0 -> this.measuredHeight
            this.minimumHeight > 0 -> this.minimumHeight
            this.layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT -> resources?.displayMetrics?.heightPixels
            else -> null
        }
    }
