package de.datlag.coilifier.commons

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.Target
import de.datlag.coilifier.Coilifier
import java.io.File

@JvmOverloads
inline fun <reified ResourceType> Activity.load(
    target: ImageView,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(any, builder)

@JvmOverloads
fun Activity.loadDrawable(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(any, builder)

@JvmOverloads
fun Activity.loadBitmap(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(any, builder)

@JvmOverloads
fun Activity.loadFile(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(any, builder)

@JvmOverloads
fun Activity.loadGif(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(any, builder)

@JvmOverloads
inline fun <reified ResourceType> Activity.load(
    target: Target<ResourceType>,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(this, any, builder)

@JvmOverloads
fun Activity.loadDrawable(
    target: Target<Drawable>,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(this, any, builder)

@JvmOverloads
fun Activity.loadBitmap(
    target: Target<Bitmap>,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(this, any, builder)

@JvmOverloads
fun Activity.loadFile(
    target: Target<File>,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(this, any, builder)

@JvmOverloads
fun Activity.loadGif(
    target: Target<GifDrawable>,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(this, any, builder)

@JvmOverloads
inline fun <reified ResourceType> Activity.preload(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@preload).apply {
        load(any, builder)
        preload()
    }
}

@JvmOverloads
fun Activity.preloadDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Activity.preloadBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Activity.preloadFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Activity.preloadGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
inline fun <reified ResourceType> Activity.submit(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@submit).apply {
        load(any, builder)
        submit()
    }
}

@JvmOverloads
fun Activity.submitDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Activity.submitBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Activity.submitFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Activity.submitGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    submit()
}