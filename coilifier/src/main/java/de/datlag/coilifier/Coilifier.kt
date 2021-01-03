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
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.BaseRequestOptions
import de.datlag.coilifier.commons.getBitmap
import java.io.File

data class Coilifier<ResourceType> internal constructor(
    @DrawableRes val errorResId: Int?,
    val errorDrawable: Drawable?,
    val errorBitmap: Bitmap?,
    @DrawableRes val placeholderResId: Int?,
    val placeholderDrawable: Drawable?,
    val placeholderBitmap: Bitmap?,
    val thumbnailSizeMultiplier: Float?,
    val thumbnailRequestBuilder: List<RequestBuilder<ResourceType>>,
    val transitionOptions: TransitionOptions<*, in ResourceType?>?,
    val transformOptions: List<Transformation<Bitmap?>>,
    val scale: Scale?,
    val requestOptions: BaseRequestOptions<*>?
) {

    class Builder<ResourceType> {
        @DrawableRes
        private var errorResId: Int? = null
        private var errorDrawable: Drawable? = null
        private var errorBitmap: Bitmap? = null

        @DrawableRes
        private var placeholderResId: Int? = null
        private var placeholderDrawable: Drawable? = null
        private var placeholderBitmap: Bitmap? = null

        private var thumbnailSizeMultiplier: Float? = null
        private var thumbnailRequestBuilder: List<RequestBuilder<ResourceType>> = listOf()

        private var transitionOptions: TransitionOptions<*, in ResourceType?>? = null
        private var transformOptions: List<Transformation<Bitmap?>> = listOf()
        private var scale: Scale? = null

        private var requestOptions: BaseRequestOptions<*>? = null

        fun error(@DrawableRes drawableResId: Int) = apply {
            this.errorResId = drawableResId
            this.errorDrawable = null
            this.errorBitmap = null
        }

        fun error(drawable: Drawable?) = apply {
            if (drawable is BitmapDrawable) {
                if (drawable.bitmap.isRecycled) return@apply
            }
            this.errorDrawable = drawable
            this.errorResId = 0
            this.errorBitmap = null
        }

        fun error(bitmap: Bitmap?) = apply {
            if (bitmap?.isRecycled == true) return@apply
            this.errorBitmap = bitmap
            this.errorDrawable = null
            this.errorResId = 0
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

        fun error(any: Any?): Builder<ResourceType> = when (any) {
            is Int -> error(any)
            is Drawable -> error(any)
            is Bitmap -> error(any)
            is View -> error(any)
            null -> clearError()
            else -> this
        }

        private fun clearError() = apply {
            this.errorBitmap = null
            this.errorDrawable = null
            this.errorResId = 0
        }

        fun placeholder(@DrawableRes drawableResId: Int) = apply {
            this.placeholderResId = drawableResId
            this.placeholderDrawable = null
            this.placeholderBitmap = null
        }

        fun placeholder(drawable: Drawable?) = apply {
            if (drawable is BitmapDrawable) {
                if (drawable.bitmap.isRecycled) return@apply
            }
            this.placeholderDrawable = drawable
            this.placeholderResId = 0
            this.placeholderBitmap = null
        }

        fun placeholder(bitmap: Bitmap?) = apply {
            if (bitmap?.isRecycled == true) return@apply
            this.placeholderBitmap = bitmap
            this.placeholderResId = 0
            this.placeholderDrawable = null
        }

        fun placeholder(view: View?) = when (view) {
            is ImageView -> {
                val bitmap = view.getBitmap()
                if (bitmap != null) {
                    placeholder(bitmap)
                } else {
                    placeholder(view.drawable)
                }
            }
            null -> clearPlaceholder()
            else -> placeholder(view.getBitmap())
        }

        fun placeholder(any: Any?) = when (any) {
            is Int -> placeholder(any)
            is Drawable -> placeholder(any)
            is Bitmap -> placeholder(any)
            is View -> placeholder(any)
            null -> clearPlaceholder()
            else -> this
        }

        private fun clearPlaceholder() = apply {
            this.placeholderBitmap = null
            this.placeholderDrawable = null
            this.placeholderResId = 0
        }

        fun thumbnail(sizeMultiplier: Float) = apply {
            this.thumbnailSizeMultiplier = sizeMultiplier
        }

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

        fun apply(requestOptions: BaseRequestOptions<*>) = apply {
            this.requestOptions = requestOptions
        }

        fun build(): Coilifier<ResourceType> {
            return Coilifier(
                errorResId,
                errorDrawable,
                errorBitmap,
                placeholderResId,
                placeholderDrawable,
                placeholderBitmap,
                thumbnailSizeMultiplier,
                thumbnailRequestBuilder,
                transitionOptions,
                transformOptions,
                scale,
                requestOptions
            )
        }
    }

    companion object {
        inline fun <reified ResourceType> with(context: Context): Loader<ResourceType> = Request<ResourceType>(context).`as`(ResourceType::class.java)
        inline fun <reified ResourceType> with(activity: Activity): Loader<ResourceType> = Request<ResourceType>(activity).`as`(ResourceType::class.java)
        inline fun <reified ResourceType> with(fragment: Fragment): Loader<ResourceType> = Request<ResourceType>(fragment).`as`(ResourceType::class.java)
        inline fun <reified ResourceType> with(view: View): Loader<ResourceType> = Request<ResourceType>(view).`as`(ResourceType::class.java)
        inline fun <reified ResourceType> with(fragmentActivity: FragmentActivity): Loader<ResourceType> = Request<ResourceType>(fragmentActivity).`as`(ResourceType::class.java)

        fun withAsDrawable(context: Context): Loader<Drawable> = Request<Drawable>(context).asDrawable()
        fun withAsDrawable(activity: Activity): Loader<Drawable> = Request<Drawable>(activity).asDrawable()
        fun withAsDrawable(fragment: Fragment): Loader<Drawable> = Request<Drawable>(fragment).asDrawable()
        fun withAsDrawable(view: View): Loader<Drawable> = Request<Drawable>(view).asDrawable()
        fun withAsDrawable(fragmentActivity: FragmentActivity): Loader<Drawable> = Request<Drawable>(fragmentActivity).asDrawable()

        fun withAsBitmap(context: Context): Loader<Bitmap> = Request<Bitmap>(context).asBitmap()
        fun withAsBitmap(activity: Activity): Loader<Bitmap> = Request<Bitmap>(activity).asBitmap()
        fun withAsBitmap(fragment: Fragment): Loader<Bitmap> = Request<Bitmap>(fragment).asBitmap()
        fun withAsBitmap(view: View): Loader<Bitmap> = Request<Bitmap>(view).asBitmap()
        fun withAsBitmap(fragmentActivity: FragmentActivity): Loader<Bitmap> = Request<Bitmap>(fragmentActivity).asBitmap()

        fun withAsFile(context: Context): Loader<File> = Request<File>(context).asFile()
        fun withAsFile(activity: Activity): Loader<File> = Request<File>(activity).asFile()
        fun withAsFile(fragment: Fragment): Loader<File> = Request<File>(fragment).asFile()
        fun withAsFile(view: View): Loader<File> = Request<File>(view).asFile()
        fun withAsFile(fragmentActivity: FragmentActivity): Loader<File> = Request<File>(fragmentActivity).asFile()

        fun withAsGif(context: Context): Loader<GifDrawable> = Request<GifDrawable>(context).asGif()
        fun withAsGif(activity: Activity): Loader<GifDrawable> = Request<GifDrawable>(activity).asGif()
        fun withAsGif(fragment: Fragment): Loader<GifDrawable> = Request<GifDrawable>(fragment).asGif()
        fun withAsGif(view: View): Loader<GifDrawable> = Request<GifDrawable>(view).asGif()
        fun withAsGif(fragmentActivity: FragmentActivity): Loader<GifDrawable> = Request<File>(fragmentActivity).asGif()
    }
}