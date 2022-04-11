package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap

internal fun View.getDrawable(): Drawable? {
    fun screenshotView(): Bitmap? {
        val drawnBitmap = try {
            this.drawToBitmap()
        } catch (ignored: Exception) {
            null
        }

        return if (drawnBitmap.isValid()) {
            drawnBitmap
        } else {
            null
        }
    }

    return if ((this.blurWidth ?: 0) > 0 && (this.blurHeight ?: 0) > 0) {
        when (this) {
            is ImageView -> {
                try {
                    val drawable = try {
                        this.drawable
                    } catch (ignored: Exception) { null }

                    if (drawable.isValid()) {
                        drawable
                    } else {
                        BitmapDrawable(this.resources, screenshotView())
                    }
                } catch (ignored: Exception) {
                    BitmapDrawable(this.resources, screenshotView())
                }
            }
            is TextureView -> {
                val drawable = try {
                    BitmapDrawable(this.resources, this.bitmap)
                } catch (ignored: Exception) { null }

                if (drawable.isValid()) {
                    drawable
                } else {
                    BitmapDrawable(this.resources, screenshotView())
                }
            }
            else -> {
                BitmapDrawable(this.resources, screenshotView())
            }
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
            this.layoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT -> resources?.displayMetrics?.widthPixels
            else -> null
        }
    }

internal val View.blurHeight: Int?
    get() {
        return when {
            this.height > 0 -> this.height
            this.measuredHeight > 0 -> this.measuredHeight
            this.layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT -> resources?.displayMetrics?.heightPixels
            else -> null
        }
    }
