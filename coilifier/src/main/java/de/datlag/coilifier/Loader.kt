package de.datlag.coilifier

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.Target
import java.io.File

class Loader<ResourceType>(
    private val requestBuilder: RequestBuilder<ResourceType>,
    private val resources: Resources?
) {

    private lateinit var appliedRequestBuilder: RequestBuilder<ResourceType>

    @JvmOverloads
    fun load(any: Any?, builder: Coilifier.Builder<ResourceType>.() -> Unit = {}): Loader<ResourceType> {
        val helper = Coilifier.Builder<ResourceType>().apply(builder).build()
        appliedRequestBuilder = if (helper.requestOptions != null) {
            requestBuilder.apply(helper.requestOptions)
        } else {
            requestBuilder
        }
        when (any) {
            is Bitmap -> appliedRequestBuilder.apply { load(any) }
            is Drawable -> appliedRequestBuilder.apply { load(any) }
            is String -> appliedRequestBuilder.apply { load(any) }
            is Int -> appliedRequestBuilder.apply { load(any) }
            is Uri -> appliedRequestBuilder.apply { load(any) }
            is File -> appliedRequestBuilder.apply { load(any) }
            is ByteArray -> appliedRequestBuilder.apply { load(any) }
            else -> appliedRequestBuilder.apply { load(any) }
        }
        loadData(helper)
        return this
    }

    private fun loadData(helper: Coilifier<ResourceType>) {
        appliedRequestBuilder.apply {
            when {
                helper.errorResId != null && helper.errorResId != 0 -> error(helper.errorResId)
                helper.errorDrawable != null -> error(helper.errorDrawable)
                helper.errorBitmap != null -> error(BitmapDrawable(resources, helper.errorBitmap))
            }
            when {
                helper.placeholderResId != null && helper.placeholderResId != 0 -> placeholder(helper.placeholderResId)
                helper.placeholderDrawable != null -> placeholder(helper.placeholderDrawable)
                helper.placeholderBitmap != null -> placeholder(BitmapDrawable(resources, helper.placeholderBitmap))
            }
            if (helper.thumbnailSizeMultiplier != null) {
                thumbnail(helper.thumbnailSizeMultiplier)
            }
            if (helper.thumbnailRequestBuilder.isNotEmpty()) {
                thumbnail(*helper.thumbnailRequestBuilder.toTypedArray())
            }
            if (helper.transitionOptions != null) {
                transition(helper.transitionOptions)
            }
            when (helper.scale) {
                Scale.FIT_CENTER -> fitCenter()
                Scale.CENTER_CROP -> centerCrop()
                Scale.CENTER_INSIDE -> centerInside()
                Scale.CIRCLE_CROP -> circleCrop()
                Scale.OPTIONAL_FIT_CENTER -> optionalFitCenter()
                Scale.OPTIONAL_CENTER_CROP -> optionalCenterCrop()
                Scale.OPTIONAL_CENTER_INSIDE -> optionalCenterInside()
                Scale.OPTIONAL_CIRCLE_CROP -> optionalCircleCrop()
            }
            transform(*helper.transformOptions.toTypedArray())
        }
    }

    fun target(view: ImageView) {
        appliedRequestBuilder.into(view)
    }

    fun target(target: Target<ResourceType>) {
        appliedRequestBuilder.into(target)
    }

    fun preload() = appliedRequestBuilder.preload()

    fun submit() = appliedRequestBuilder.submit()

}