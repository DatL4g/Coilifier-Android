package de.datlag.coilifier

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestListener
import de.datlag.coilifier.commons.blurHeight
import de.datlag.coilifier.commons.blurWidth
import de.datlag.coilifier.commons.getBitmap
import java.io.File

data class Coilifier<ResourceType> internal constructor(
    @DrawableRes val errorResId: Int?,
    val errorDrawable: Drawable?,
    val errorBitmap: Bitmap?,
    val errorRequest: RequestBuilder<ResourceType>?,
    @DrawableRes val placeholderResId: Int?,
    val placeholderDrawable: Drawable?,
    val placeholderBitmap: Bitmap?,
    val placeholderScaling: PlaceholderScaling?,
    @DrawableRes val fallbackResId: Int?,
    val fallbackDrawable: Drawable?,
    val fallbackBitmap: Bitmap?,
    val requestListener: List<RequestListener<ResourceType>>,
    val thumbnailSizeMultiplier: Float?,
    val thumbnailRequestBuilder: List<RequestBuilder<ResourceType>>,
    val transitionOptions: TransitionOptions<*, in ResourceType?>?,
    val transformOptions: List<Transformation<Bitmap?>>,
    val scale: Scale?,
    val overrideWidth: Int?,
    val overrideHeight: Int?,
    val downsampleStrategy: DownsampleStrategy?,
    val encodeFormat: Bitmap.CompressFormat?,
    val encodeQuality: Int?,
    val dontAnimate: Boolean,
    val dontTransform: Boolean,
    val clone: Boolean,
    val autoClone: Boolean,
    val decodeClass: Class<*>?,
    val diskCacheStrategy: DiskCacheStrategy?,
    val disallowHardwareConfig: Boolean,
    val requestOptions: BaseRequestOptions<*>?
) {

    class Builder<ResourceType> {
        @DrawableRes
        private var errorResId: Int? = null
        private var errorDrawable: Drawable? = null
        private var errorBitmap: Bitmap? = null
        private var errorRequest: RequestBuilder<ResourceType>? = null

        @DrawableRes
        private var placeholderResId: Int? = null
        private var placeholderDrawable: Drawable? = null
        private var placeholderBitmap: Bitmap? = null
        private var placeholderScaling: PlaceholderScaling? = null

        @DrawableRes
        private var fallbackResId: Int? = null
        private var fallbackDrawable: Drawable? = null
        private var fallbackBitmap: Bitmap? = null

        private var requestListener: List<RequestListener<ResourceType>> = listOf()
        private var thumbnailSizeMultiplier: Float? = null
        private var thumbnailRequestBuilder: List<RequestBuilder<ResourceType>> = listOf()

        private var transitionOptions: TransitionOptions<*, in ResourceType?>? = null
        private var transformOptions: List<Transformation<Bitmap?>> = listOf()
        private var scale: Scale? = null

        private var overrideWidth: Int? = null
        private var overrideHeight: Int? = null

        private var downsampleStrategy: DownsampleStrategy? = null
        private var encodeFormat: Bitmap.CompressFormat? = null
        private var encodeQuality: Int? = null

        private var dontAnimate: Boolean = false
        private var dontTransform: Boolean = false
        private var clone: Boolean = false
        private var autoClone: Boolean = false

        private var decodeClass: Class<*>? = null

        private var diskCacheStrategy: DiskCacheStrategy? = null
        private var disallowHardwareConfig: Boolean = false
        private var requestOptions: BaseRequestOptions<*>? = null

        private var blurHash: BlurHash? = null

        fun blurHash(blurHash: BlurHash) = apply {
            this.blurHash = blurHash
        }

        private fun getNewBlurHash(newBlurHash: BlurHash? = this.blurHash): BlurHash {
            return when {
                blurHash == null && newBlurHash != null -> {
                    blurHash = newBlurHash
                    blurHash!!
                }
                blurHash != null && newBlurHash == null -> {
                    blurHash!!
                }
                blurHash == null && newBlurHash == null -> {
                    throw IllegalArgumentException("No BlurHash provided, use the blurHash(...) builder method (At the very top) or pass it in the function.")
                }
                blurHash?.isSame(newBlurHash) == true -> {
                    blurHash!!
                }
                else -> newBlurHash ?: this.blurHash!!
            }
        }

        fun error(@DrawableRes drawableResId: Int) = apply {
            this.errorResId = drawableResId
            this.errorDrawable = null
            this.errorBitmap = null
            this.errorRequest = null
        }

        fun error(drawable: Drawable?) = apply {
            if (drawable is BitmapDrawable) {
                if (drawable.bitmap == null) return@apply
                if (drawable.bitmap.isRecycled) return@apply
            }
            this.errorDrawable = drawable
            this.errorResId = 0
            this.errorBitmap = null
            this.errorRequest = null
        }

        fun error(bitmap: Bitmap?) = apply {
            if (bitmap?.isRecycled == true) return@apply
            this.errorBitmap = bitmap
            this.errorDrawable = null
            this.errorResId = 0
            this.errorRequest = null
        }

        fun error(view: View?) = when (view) {
            is ImageView -> {
                val bitmap = view.getBitmap()
                if (bitmap != null) {
                    error(bitmap)
                } else {
                    error(view.drawable)
                }
            }
            null -> clearError()
            else -> error(view.getBitmap())
        }

        fun error(requestBuilder: RequestBuilder<ResourceType>) = apply {
            this.errorResId = 0
            this.errorDrawable = null
            this.errorBitmap = null
            this.errorRequest = requestBuilder
        }

        fun error(blurString: String, width: Int, height: Int, blurHash: BlurHash) = apply {
            if (width > 0 && height > 0) {
                error(getNewBlurHash(blurHash).execute(blurString, width, height))
            }
        }

        fun error(blurString: String, width: Int, height: Int) = apply {
            error(blurString, width, height, getNewBlurHash())
        }

        @JvmOverloads
        fun error(blurString: String, view: View, blurHash: BlurHash = this.blurHash ?: BlurHash(view.context)) = apply {
            val width = view.blurWidth
            val height = view.blurHeight

            if (width != null && height != null) {
                error(blurString, width, height, blurHash)
            } else if (width != null && height == null) {
                error(blurString, width, width, blurHash)
            } else if (width == null && height != null) {
                error(blurString, height, height, blurHash)
            } else {
                view.post {
                    error(blurString, view.blurWidth ?: 0, view.blurHeight ?: 0, blurHash)
                }
            }
        }

        fun error(any: Any?): Builder<ResourceType> = when (any) {
            is Int -> error(any)
            is Drawable -> error(any)
            is Bitmap -> error(any)
            is View -> error(any)
            is RequestBuilder<*> -> error(any)
            null -> clearError()
            else -> this
        }

        private fun clearError() = apply {
            this.errorBitmap = null
            this.errorDrawable = null
            this.errorResId = 0
            this.errorRequest = null
        }

        @JvmOverloads
        fun placeholder(@DrawableRes drawableResId: Int, scaling: PlaceholderScaling? = null) = apply {
            this.placeholderResId = drawableResId
            this.placeholderDrawable = null
            this.placeholderBitmap = null
            this.placeholderScaling = scaling
        }

        @JvmOverloads
        fun placeholder(drawable: Drawable?, scaling: PlaceholderScaling? = null) = apply {
            if (drawable is BitmapDrawable) {
                if (drawable.bitmap == null) return@apply
                if (drawable.bitmap.isRecycled) return@apply
            }
            this.placeholderDrawable = drawable
            this.placeholderResId = 0
            this.placeholderBitmap = null
            this.placeholderScaling = scaling
        }

        @JvmOverloads
        fun placeholder(bitmap: Bitmap?, scaling: PlaceholderScaling? = null) = apply {
            if (bitmap?.isRecycled == true) return@apply
            this.placeholderBitmap = bitmap
            this.placeholderResId = 0
            this.placeholderDrawable = null
            this.placeholderScaling = scaling
        }

        @JvmOverloads
        fun placeholder(view: View?, scaling: PlaceholderScaling? = null) = when (view) {
            is ImageView -> {
                val bitmap = view.getBitmap()
                if (bitmap != null) {
                    placeholder(bitmap, scaling)
                } else {
                    placeholder(view.drawable, scaling)
                }
            }
            null -> clearPlaceholder()
            else -> placeholder(view.getBitmap(), scaling)
        }

        fun placeholder(view: View?, scaleByWidth: Boolean?) = when (view) {
            is ImageView -> {
                val bitmap = view.getBitmap()
                if (bitmap != null) {
                    placeholder(bitmap, PlaceholderScaling.fitCenter(view, scaleByWidth ?: true))
                } else {
                    placeholder(view.drawable, PlaceholderScaling.fitCenter(view, scaleByWidth ?: true))
                }
            }
            null -> clearPlaceholder()
            else -> placeholder(view.getBitmap(), PlaceholderScaling.fitCenter(view, scaleByWidth ?: true))
        }

        @JvmOverloads
        fun placeholder(blurString: String, width: Int, height: Int, blurHash: BlurHash, scaling: PlaceholderScaling? = null) = apply {
            if (width > 0 && height > 0) {
                placeholder(getNewBlurHash(blurHash).execute(blurString, width, height), scaling)
            }
        }

        @JvmOverloads
        fun placeholder(blurString: String, width: Int, height: Int, scaling: PlaceholderScaling? = null) = apply {
            placeholder(blurString, width, height, getNewBlurHash(), scaling)
        }

        @JvmOverloads
        fun placeholder(blurString: String, view: View, blurHash: BlurHash = this.blurHash ?: BlurHash(view.context), scaling: PlaceholderScaling? = null) = apply {
            val width = view.blurWidth
            val height = view.blurHeight

            if (width != null && height != null) {
                placeholder(blurString, width, height, blurHash, scaling)
            } else if (width != null && height == null) {
                placeholder(blurString, width, width, blurHash, scaling)
            } else if (width == null && height != null) {
                placeholder(blurString, height, height, blurHash, scaling)
            } else {
                view.post {
                    placeholder(blurString, view.blurWidth ?: 0, view.blurHeight ?: 0, blurHash, scaling)
                }
            }
        }

        @JvmOverloads
        fun placeholder(any: Any?, scaling: PlaceholderScaling? = null) = when (any) {
            is Int -> placeholder(any, scaling)
            is Drawable -> placeholder(any, scaling)
            is Bitmap -> placeholder(any, scaling)
            is View -> placeholder(any, scaling)
            null -> clearPlaceholder()
            else -> this
        }

        private fun clearPlaceholder() = apply {
            this.placeholderBitmap = null
            this.placeholderDrawable = null
            this.placeholderResId = 0
            this.placeholderScaling = null
        }

        fun fallback(@DrawableRes drawableResId: Int) = apply {
            this.fallbackResId = drawableResId
            this.fallbackDrawable = null
            this.fallbackBitmap = null
        }

        fun fallback(drawable: Drawable?) = apply {
            if (drawable is BitmapDrawable) {
                if (drawable.bitmap == null) return@apply
                if (drawable.bitmap.isRecycled) return@apply
            }
            this.fallbackDrawable = drawable
            this.fallbackResId = 0
            this.fallbackBitmap = null
        }

        fun fallback(bitmap: Bitmap?) = apply {
            if (bitmap?.isRecycled == true) return@apply
            this.fallbackBitmap = bitmap
            this.fallbackResId = 0
            this.fallbackDrawable = null
        }

        fun fallback(view: View?) = when (view) {
            is ImageView -> {
                val bitmap = view.getBitmap()
                if (bitmap != null) {
                    fallback(bitmap)
                } else {
                    fallback(view.drawable)
                }
            }
            null -> clearFallback()
            else -> fallback(view.getBitmap())
        }

        fun fallback(blurString: String, width: Int, height: Int, blurHash: BlurHash) = apply {
            if (width > 0 && height > 0) {
                fallback(getNewBlurHash(blurHash).execute(blurString, width, height))
            }
        }

        fun fallback(blurString: String, width: Int, height: Int) = apply {
            fallback(blurString, width, height, getNewBlurHash())
        }

        @JvmOverloads
        fun fallback(blurString: String, view: View, blurHash: BlurHash = this.blurHash ?: BlurHash(view.context)) = apply {
            val width = view.blurWidth
            val height = view.blurHeight

            if (width != null && height != null) {
                fallback(blurString, width, height, blurHash)
            } else if (width != null && height == null) {
                fallback(blurString, width, width, blurHash)
            } else if (width == null && height != null) {
                fallback(blurString, height, height, blurHash)
            } else {
                view.post {
                    fallback(blurString, view.blurWidth ?: 0, view.blurHeight ?: 0, blurHash)
                }
            }
        }

        fun fallback(any: Any?) = when (any) {
            is Int -> fallback(any)
            is Drawable -> fallback(any)
            is Bitmap -> fallback(any)
            is View -> fallback(any)
            null -> clearFallback()
            else -> this
        }

        private fun clearFallback() = apply {
            this.fallbackResId = 0
            this.fallbackDrawable = null
            this.fallbackBitmap = null
        }

        fun listener(vararg requestListener: RequestListener<ResourceType>) = apply {
            this.requestListener = requestListener.toList()
        }

        fun thumbnail(sizeMultiplier: Float) = apply {
            this.thumbnailSizeMultiplier = sizeMultiplier
        }

        fun thumbnail(sizeMultiplier: Double) = thumbnail(sizeMultiplier.toFloat())

        fun thumbnail(vararg thumbnailRequestBuilder: RequestBuilder<ResourceType>) = apply {
            this.thumbnailRequestBuilder = thumbnailRequestBuilder.toList()
        }

        fun transition(options: TransitionOptions<*, in ResourceType?>) = apply {
            this.transitionOptions = options
        }

        fun transform(vararg transformations: Transformation<Bitmap?>) = apply {
            this.transformOptions = transformations.toList()
        }

        fun scaleType(scale: Scale) = apply {
            this.scale = scale
        }

        fun fitCenter() = apply {
            this.scale = Scale.FIT_CENTER
        }

        fun centerCrop() = apply {
            this.scale = Scale.CENTER_CROP
        }

        fun centerInside() = apply {
            this.scale = Scale.CENTER_INSIDE
        }

        fun circleCrop() = apply {
            this.scale = Scale.CIRCLE_CROP
        }

        fun optionalFitCenter() = apply {
            this.scale = Scale.OPTIONAL_FIT_CENTER
        }

        fun optionalCenterCrop() = apply {
            this.scale = Scale.OPTIONAL_CENTER_CROP
        }

        fun optionalCenterInside() = apply {
            this.scale = Scale.OPTIONAL_CENTER_INSIDE
        }

        fun optionalCircleCrop() = apply {
            this.scale = Scale.OPTIONAL_CIRCLE_CROP
        }

        fun override(width: Int, height: Int) = apply {
            this.overrideWidth = width
            this.overrideHeight = height
        }

        fun override(width: Long, height: Long) = override(width.toInt(), height.toInt())
        fun override(width: Float, height: Float) = override(width.toInt(), height.toInt())
        fun override(width: Double, height: Double) = override(width.toInt(), height.toInt())

        fun override(size: Int) = override(size, size)
        fun override(size: Long) = override(size, size)
        fun override(size: Float) = override(size, size)
        fun override(size: Double) = override(size, size)

        fun downsample(strategy: DownsampleStrategy) = apply {
            this.downsampleStrategy = strategy
        }

        fun encodeFormat(format: Bitmap.CompressFormat) = apply {
            this.encodeFormat = format
        }

        fun encodeQuality(quality: Int) = apply {
            this.encodeQuality = quality
        }

        fun encodeQuality(quality: Long) = encodeQuality(quality.toInt())
        fun encodeQuality(quality: Float) = encodeQuality(quality.toInt())
        fun encodeQuality(quality: Double) = encodeQuality(quality.toInt())

        @JvmOverloads
        fun animate(`do`: Boolean = true) = apply {
            this.dontAnimate = !`do`
        }

        fun dontAnimate() = animate(false)

        @JvmOverloads
        fun transform(`do`: Boolean = true) = apply {
            this.dontTransform = !`do`
        }

        fun dontTransform() = transform(false)

        @JvmOverloads
        fun clone(`do`: Boolean = true) = apply {
            this.clone = `do`
        }

        @JvmOverloads
        fun autoClone(`do`: Boolean = true) = apply {
            this.autoClone = `do`
        }

        fun decode(resourceClass: Class<*>) = apply {
            this.decodeClass = resourceClass
        }

        fun diskCacheStrategy(strategy: DiskCacheStrategy) = apply {
            this.diskCacheStrategy = strategy
        }

        @JvmOverloads
        fun hardwareConfig(allow: Boolean = true) = apply {
            this.disallowHardwareConfig = !allow
        }

        fun disallowHardwareConfig() = hardwareConfig(false)

        fun apply(requestOptions: BaseRequestOptions<*>) = apply {
            this.requestOptions = requestOptions
        }

        internal fun build() = Coilifier(
            errorResId,
            errorDrawable,
            errorBitmap,
            errorRequest,
            placeholderResId,
            placeholderDrawable,
            placeholderBitmap,
            placeholderScaling,
            fallbackResId,
            fallbackDrawable,
            fallbackBitmap,
            requestListener,
            thumbnailSizeMultiplier,
            thumbnailRequestBuilder,
            transitionOptions,
            transformOptions,
            scale,
            overrideWidth,
            overrideHeight,
            downsampleStrategy,
            encodeFormat,
            encodeQuality,
            dontAnimate,
            dontTransform,
            clone,
            autoClone,
            decodeClass,
            diskCacheStrategy,
            disallowHardwareConfig,
            requestOptions
        )
    }

    companion object {
        @JvmStatic
        inline fun <reified ResourceType> with(context: Context): Loader<ResourceType> =
            Request<ResourceType>(context).`as`(ResourceType::class.java)

        @JvmStatic
        inline fun <reified ResourceType> with(activity: Activity): Loader<ResourceType> =
            Request<ResourceType>(activity).`as`(ResourceType::class.java)

        @JvmStatic
        inline fun <reified ResourceType> with(fragment: Fragment): Loader<ResourceType> =
            Request<ResourceType>(fragment).`as`(ResourceType::class.java)

        @JvmStatic
        inline fun <reified ResourceType> with(view: View): Loader<ResourceType> =
            Request<ResourceType>(view).`as`(ResourceType::class.java)

        @JvmStatic
        inline fun <reified ResourceType> with(fragmentActivity: FragmentActivity): Loader<ResourceType> =
            Request<ResourceType>(fragmentActivity).`as`(ResourceType::class.java)

        @JvmStatic
        fun withAsDrawable(context: Context): Loader<Drawable> =
            Request<Drawable>(context).asDrawable()

        @JvmStatic
        fun withAsDrawable(activity: Activity): Loader<Drawable> =
            Request<Drawable>(activity).asDrawable()

        @JvmStatic
        fun withAsDrawable(fragment: Fragment): Loader<Drawable> =
            Request<Drawable>(fragment).asDrawable()

        @JvmStatic
        fun withAsDrawable(view: View): Loader<Drawable> = Request<Drawable>(view).asDrawable()
        @JvmStatic
        fun withAsDrawable(fragmentActivity: FragmentActivity): Loader<Drawable> =
            Request<Drawable>(fragmentActivity).asDrawable()

        @JvmStatic
        fun withAsBitmap(context: Context): Loader<Bitmap> = Request<Bitmap>(context).asBitmap()
        @JvmStatic
        fun withAsBitmap(activity: Activity): Loader<Bitmap> = Request<Bitmap>(activity).asBitmap()
        @JvmStatic
        fun withAsBitmap(fragment: Fragment): Loader<Bitmap> = Request<Bitmap>(fragment).asBitmap()
        @JvmStatic
        fun withAsBitmap(view: View): Loader<Bitmap> = Request<Bitmap>(view).asBitmap()
        @JvmStatic
        fun withAsBitmap(fragmentActivity: FragmentActivity): Loader<Bitmap> =
            Request<Bitmap>(fragmentActivity).asBitmap()

        @JvmStatic
        fun withAsFile(context: Context): Loader<File> = Request<File>(context).asFile()
        @JvmStatic
        fun withAsFile(activity: Activity): Loader<File> = Request<File>(activity).asFile()
        @JvmStatic
        fun withAsFile(fragment: Fragment): Loader<File> = Request<File>(fragment).asFile()
        @JvmStatic
        fun withAsFile(view: View): Loader<File> = Request<File>(view).asFile()
        @JvmStatic
        fun withAsFile(fragmentActivity: FragmentActivity): Loader<File> =
            Request<File>(fragmentActivity).asFile()

        @JvmStatic
        fun withAsGif(context: Context): Loader<GifDrawable> = Request<GifDrawable>(context).asGif()
        @JvmStatic
        fun withAsGif(activity: Activity): Loader<GifDrawable> =
            Request<GifDrawable>(activity).asGif()

        @JvmStatic
        fun withAsGif(fragment: Fragment): Loader<GifDrawable> =
            Request<GifDrawable>(fragment).asGif()

        @JvmStatic
        fun withAsGif(view: View): Loader<GifDrawable> = Request<GifDrawable>(view).asGif()
        @JvmStatic
        fun withAsGif(fragmentActivity: FragmentActivity): Loader<GifDrawable> =
            Request<File>(fragmentActivity).asGif()
    }
}