package de.datlag.coilifier

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.LruCache

const val LUR_SIZE = 10

class BlurHash(
    private var context: Context,
    lruSize: Int = LUR_SIZE,
    private var punch: Float = 1F
) {
    private var data: LruCache<String, BitmapDrawable> = LruCache(lruSize)

    fun clean() {
        data.evictAll()
        BlurHashDecoder.clearCache()
    }

    fun execute(
        blurString: String,
        width: Int,
        height: Int,
    ): BitmapDrawable {
        return getBlurDrawable(blurString) ?: run {
            val bitmap = BlurHashDecoder.decode(
                blurString,
                width,
                height,
                punch
            )
            val drawable = BitmapDrawable(
                context.resources,
                bitmap
            )
            cache(blurString, drawable)
            drawable
        }
    }

    private fun cache(blurString: String, drawable: BitmapDrawable) {
        data.put(blurString, drawable)
    }

    private fun getBlurDrawable(blurString: String): BitmapDrawable? {
        return data.get(blurString)
    }
}