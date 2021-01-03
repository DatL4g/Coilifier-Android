package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.Target
import de.datlag.coilifier.Coilifier
import java.io.File

@JvmOverloads
inline fun <reified ResourceType> Fragment.load(
    target: ImageView,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(any, builder)

@JvmOverloads
fun Fragment.loadDrawable(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(any, builder)

@JvmOverloads
fun Fragment.loadBitmap(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(any, builder)

@JvmOverloads
fun Fragment.loadFile(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(any, builder)

@JvmOverloads
fun Fragment.loadGif(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(any, builder)

@JvmOverloads
inline fun <reified ResourceType> Fragment.load(
    target: Target<ResourceType>,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(this, any, builder)

@JvmOverloads
fun Fragment.loadDrawable(
    target: Target<Drawable>,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(this, any, builder)

@JvmOverloads
fun Fragment.loadBitmap(
    target: Target<Bitmap>,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(this, any, builder)

@JvmOverloads
fun Fragment.loadFile(
    target: Target<File>,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(this, any, builder)

@JvmOverloads
fun Fragment.loadGif(
    target: Target<GifDrawable>,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(this, any, builder)

@JvmOverloads
inline fun <reified ResourceType> Fragment.preload(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@preload).apply {
        load(any, builder)
        preload()
    }
}

@JvmOverloads
fun Fragment.preloadDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Fragment.preloadBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Fragment.preloadFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun Fragment.preloadGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
inline fun <reified ResourceType> Fragment.submit(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@submit).apply {
        load(any, builder)
        submit()
    }
}

@JvmOverloads
fun Fragment.submitDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Fragment.submitBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Fragment.submitFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun Fragment.submitGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    submit()
}