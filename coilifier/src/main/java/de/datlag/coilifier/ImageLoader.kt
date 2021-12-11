package de.datlag.coilifier

sealed class ImageLoader {
    internal class Any(val any: kotlin.Any?) : ImageLoader()
    class Bitmap(val bitmap: android.graphics.Bitmap) : ImageLoader()
    class ByteArray(val byteArray: kotlin.ByteArray) : ImageLoader()
    class Drawable(val drawable: android.graphics.drawable.Drawable) : ImageLoader()
    class File(val file: java.io.File) : ImageLoader()
    class Resource(val resId: Int) : ImageLoader()
    class String(val uri: kotlin.String) : ImageLoader()
    class Uri(val uri: android.net.Uri) : ImageLoader()
    class View(val view: android.view.View) : ImageLoader()

    companion object {
        fun create(any: kotlin.Any?): ImageLoader = when (any) {
            is android.graphics.Bitmap -> Bitmap(any)
            is kotlin.ByteArray -> ByteArray(any)
            is android.graphics.drawable.Drawable -> Drawable(any)
            is java.io.File -> File(any)
            is Int -> Resource(any)
            is kotlin.String -> String(any)
            is android.net.Uri -> Uri(any)
            is android.view.View -> View(any)
            else -> Any(any)
        }
    }
}
