package de.datlag.coilifier.commons

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.Target
import de.datlag.coilifier.Coilifier
import java.io.File

@JvmOverloads
inline fun <reified ResourceType> Target<ResourceType>.load(
    context: Context,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.with<ResourceType>(context).apply {
    load(any, builder)
    target(this@load)
}

@JvmOverloads
inline fun <reified ResourceType> Target<ResourceType>.load(
    activity: Activity,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.with<ResourceType>(activity).apply {
    load(any, builder)
    target(this@load)
}

@JvmOverloads
inline fun <reified ResourceType> Target<ResourceType>.load(
    fragment: Fragment,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.with<ResourceType>(fragment).apply {
    load(any, builder)
    target(this@load)
}

@JvmOverloads
inline fun <reified ResourceType> Target<ResourceType>.load(
    view: View,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.with<ResourceType>(view).apply {
    load(any, builder)
    target(this@load)
}

@JvmOverloads
inline fun <reified ResourceType> Target<ResourceType>.load(
    fragmentActivity: FragmentActivity,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = Coilifier.with<ResourceType>(fragmentActivity).apply {
    load(any, builder)
    target(this@load)
}

@JvmOverloads
@Throws(IllegalArgumentException::class)
inline fun <reified ResourceType> Target<ResourceType>.load(
    context: Any?,
    any: Any?,
    noinline builder: Coilifier.Builder<ResourceType>.() -> Unit = {}
) = when (context) {
    is Context -> this.load(context, any, builder)
    is Activity -> this.load(context, any, builder)
    is Fragment -> this.load(context, any, builder)
    is View -> this.load(context, any, builder)
    is FragmentActivity -> this.load(context, any, builder)
    else -> throw IllegalArgumentException("passed context cannot be used")
}

@JvmOverloads
fun Target<Drawable>.loadDrawable(
    context: Context,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(context).apply {
    load(any, builder)
    target(this@loadDrawable)
}

@JvmOverloads
fun Target<Drawable>.loadDrawable(
    activity: Activity,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(activity).apply {
    load(any, builder)
    target(this@loadDrawable)
}

@JvmOverloads
fun Target<Drawable>.loadDrawable(
    fragment: Fragment,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(fragment).apply {
    load(any, builder)
    target(this@loadDrawable)
}

@JvmOverloads
fun Target<Drawable>.loadDrawable(
    view: View,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(view).apply {
    load(any, builder)
    target(this@loadDrawable)
}

@JvmOverloads
fun Target<Drawable>.loadDrawable(
    fragmentActivity: FragmentActivity,
    any: Any?,
    builder: Coilifier.Builder<Drawable>.() -> Unit = {}
) = Coilifier.withAsDrawable(fragmentActivity).apply {
    load(any, builder)
    target(this@loadDrawable)
}

@JvmOverloads
fun Target<Bitmap>.loadBitmap(
    context: Context,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(context).apply {
    load(any, builder)
    target(this@loadBitmap)
}

@JvmOverloads
fun Target<Bitmap>.loadBitmap(
    activity: Activity,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(activity).apply {
    load(any, builder)
    target(this@loadBitmap)
}

@JvmOverloads
fun Target<Bitmap>.loadBitmap(
    fragment: Fragment,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(fragment).apply {
    load(any, builder)
    target(this@loadBitmap)
}

@JvmOverloads
fun Target<Bitmap>.loadBitmap(
    view: View,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(view).apply {
    load(any, builder)
    target(this@loadBitmap)
}

@JvmOverloads
fun Target<Bitmap>.loadBitmap(
    fragmentActivity: FragmentActivity,
    any: Any?,
    builder: Coilifier.Builder<Bitmap>.() -> Unit = {}
) = Coilifier.withAsBitmap(fragmentActivity).apply {
    load(any, builder)
    target(this@loadBitmap)
}

@JvmOverloads
fun Target<File>.loadFile(
    context: Context,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(context).apply {
    load(any, builder)
    target(this@loadFile)
}

@JvmOverloads
fun Target<File>.loadFile(
    activity: Activity,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(activity).apply {
    load(any, builder)
    target(this@loadFile)
}

@JvmOverloads
fun Target<File>.loadFile(
    fragment: Fragment,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(fragment).apply {
    load(any, builder)
    target(this@loadFile)
}

@JvmOverloads
fun Target<File>.loadFile(
    view: View,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(view).apply {
    load(any, builder)
    target(this@loadFile)
}

@JvmOverloads
fun Target<File>.loadFile(
    fragmentActivity: FragmentActivity,
    any: Any?,
    builder: Coilifier.Builder<File>.() -> Unit = {}
) = Coilifier.withAsFile(fragmentActivity).apply {
    load(any, builder)
    target(this@loadFile)
}

@JvmOverloads
fun Target<GifDrawable>.loadGif(
    context: Context,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(context).apply {
    load(any, builder)
    target(this@loadGif)
}

@JvmOverloads
fun Target<GifDrawable>.loadGif(
    activity: Activity,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(activity).apply {
    load(any, builder)
    target(this@loadGif)
}

@JvmOverloads
fun Target<GifDrawable>.loadGif(
    fragment: Fragment,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(fragment).apply {
    load(any, builder)
    target(this@loadGif)
}

@JvmOverloads
fun Target<GifDrawable>.loadGif(
    view: View,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(view).apply {
    load(any, builder)
    target(this@loadGif)
}

@JvmOverloads
fun Target<GifDrawable>.loadGif(
    fragmentActivity: FragmentActivity,
    any: Any?,
    builder: Coilifier.Builder<GifDrawable>.() -> Unit = {}
) = Coilifier.withAsGif(fragmentActivity).apply {
    load(any, builder)
    target(this@loadGif)
}