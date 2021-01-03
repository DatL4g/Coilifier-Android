package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.resource.gif.GifDrawable
import de.datlag.coilifier.Coilifier
import java.io.File

@JvmOverloads
inline fun <reified ResourceType> ImageView.load(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.with<ResourceType>(this@load).apply {
    load(any, builder)
    target(this@load)
}

@JvmOverloads
fun ImageView.loadDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this@loadDrawable).apply {
    load(any, builder)
    target(this@loadDrawable)
}

@JvmOverloads
fun ImageView.loadBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this@loadBitmap).apply {
    load(any, builder)
    target(this@loadBitmap)
}

@JvmOverloads
fun ImageView.loadFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this@loadFile).apply {
    load(any, builder)
    target(this@loadFile)
}

@JvmOverloads
fun ImageView.loadGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this@loadGif).apply {
    load(any, builder)
    target(this@loadGif)
}
