package de.datlag.coilifier.commons

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.Target
import de.datlag.coilifier.Coilifier
import java.io.File

@JvmOverloads
inline fun <reified ResourceType> FragmentActivity.load(
    target: ImageView,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(any, builder)

@JvmOverloads
fun FragmentActivity.loadDrawable(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(any, builder)

@JvmOverloads
fun FragmentActivity.loadBitmap(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(any, builder)

@JvmOverloads
fun FragmentActivity.loadFile(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(any, builder)

@JvmOverloads
fun FragmentActivity.loadGif(
    target: ImageView,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(any, builder)

@JvmOverloads
inline fun <reified ResourceType> FragmentActivity.load(
    target: Target<ResourceType>,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = target.load(this, any, builder)

@JvmOverloads
fun FragmentActivity.loadDrawable(
    target: Target<Drawable>,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = target.loadDrawable(this, any, builder)

@JvmOverloads
fun FragmentActivity.loadBitmap(
    target: Target<Bitmap>,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = target.loadBitmap(this, any, builder)

@JvmOverloads
fun FragmentActivity.loadFile(
    target: Target<File>,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = target.loadFile(this, any, builder)

@JvmOverloads
fun FragmentActivity.loadGif(
    target: Target<GifDrawable>,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = target.loadGif(this, any, builder)

@JvmOverloads
inline fun <reified ResourceType> FragmentActivity.preload(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@preload).apply {
        load(any, builder)
        preload()
    }
}

@JvmOverloads
fun FragmentActivity.preloadDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun FragmentActivity.preloadBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun FragmentActivity.preloadFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
fun FragmentActivity.preloadGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    preload()
}

@JvmOverloads
inline fun <reified ResourceType> FragmentActivity.submit(
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.apply {
    with<ResourceType>(this@submit).apply {
        load(any, builder)
        submit()
    }
}

@JvmOverloads
fun FragmentActivity.submitDrawable(
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun FragmentActivity.submitBitmap(
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun FragmentActivity.submitFile(
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(this).apply {
    load(any, builder)
    submit()
}

@JvmOverloads
fun FragmentActivity.submitGif(
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(this).apply {
    load(any, builder)
    submit()
}