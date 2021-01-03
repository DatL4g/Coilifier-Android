package de.datlag.coilifier.commons

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.Target
import de.datlag.coilifier.Coilifier
import java.io.File

@JvmOverloads
inline fun <reified ResourceType> Context.load(
    target: ImageView,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(any, builder)

@JvmOverloads
fun Context.loadDrawable(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(any, builder)

@JvmOverloads
fun Context.loadBitmap(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(any, builder)

@JvmOverloads
fun Context.loadFile(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(any, builder)

@JvmOverloads
fun Context.loadGif(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(any, builder)

@JvmOverloads
inline fun <reified ResourceType> Context.load(
    target: Target<ResourceType>,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(this, any, builder)

@JvmOverloads
fun Context.loadDrawable(
    target: Target<Drawable>,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(this, any, builder)

@JvmOverloads
fun Context.loadBitmap(
    target: Target<Bitmap>,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(this, any, builder)

@JvmOverloads
fun Context.loadFile(
    target: Target<File>,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(this, any, builder)

@JvmOverloads
fun Context.loadGif(
    target: Target<GifDrawable>,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(this, any, builder)

@JvmOverloads
inline fun <reified ResourceType> Context.preload(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@preload).apply {
        load(any, builder)
        preload()
    }
}

@JvmOverloads
fun Context.preloadDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Context.preloadBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Context.preloadFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Context.preloadGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
inline fun <reified ResourceType> Context.submit(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@submit).apply {
        load(any, builder)
        submit()
    }
}

@JvmOverloads
fun Context.submitDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Context.submitBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Context.submitFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Context.submitGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    submit()
}