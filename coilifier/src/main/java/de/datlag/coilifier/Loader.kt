package de.datlag.coilifier

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.Target
import de.datlag.coilifier.commons.scale
import java.io.File

class Loader<ResourceType>(
    private val requestBuilder: RequestBuilder<ResourceType>,
    private val resources: Resources?
) {

    private lateinit var appliedRequestBuilder: RequestBuilder<ResourceType>

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
    private fun loadData(helper: Coilifier<ResourceType>) {
        appliedRequestBuilder.apply {
            if (helper.disallowHardwareConfig) {
                disallowHardwareConfig()
            }
            when {
                helper.errorResId != null && helper.errorResId != 0 -> error(helper.errorResId)
                helper.errorDrawable != null -> error(helper.errorDrawable)
                helper.errorBitmap != null -> error(BitmapDrawable(resources, helper.errorBitmap))
                helper.errorRequest != null -> error(helper.errorRequest)
            }
            if (helper.placeholderScaling != null && helper.placeholderScaling.max) {
                val scaleBitmap = when {
                    helper.placeholderResId != null && helper.placeholderResId != 0 -> BitmapFactory.decodeResource(resources, helper.placeholderResId)
                    helper.placeholderDrawable != null -> helper.placeholderDrawable.toBitmap()
                    helper.placeholderBitmap != null -> helper.placeholderBitmap
                    else -> null
                }
                if (helper.placeholderScaling.size != null && helper.placeholderScaling.scaleByWidth != null) {
                    placeholder(BitmapDrawable(resources, scaleBitmap?.scale(helper.placeholderScaling.size, helper.placeholderScaling.scaleByWidth)))
                } else {
                    val maxWidthPixel: Int? = resources?.displayMetrics?.widthPixels
                    val maxHeightPixel: Int? = resources?.displayMetrics?.heightPixels
                    when {
                        maxWidthPixel != null -> {
                            placeholder(BitmapDrawable(resources, scaleBitmap?.scale(maxWidthPixel, true)))
                        }
                        maxHeightPixel != null -> {
                            placeholder(BitmapDrawable(resources, scaleBitmap?.scale(maxHeightPixel, false)))
                        }
                        else -> {
                            placeholder(BitmapDrawable(resources, scaleBitmap))
                        }
                    }
                }
            } else {
                when {
                    helper.placeholderResId != null && helper.placeholderResId != 0 -> placeholder(helper.placeholderResId)
                    helper.placeholderDrawable != null -> placeholder(helper.placeholderDrawable)
                    helper.placeholderBitmap != null -> placeholder(BitmapDrawable(resources, helper.placeholderBitmap))
                }
            }

            when {
                helper.fallbackResId != null && helper.fallbackResId != 0 -> fallback(helper.fallbackResId)
                helper.fallbackDrawable != null -> fallback(helper.fallbackDrawable)
                helper.fallbackBitmap != null -> fallback(BitmapDrawable(resources, helper.fallbackBitmap))
            }
            if (helper.requestListener.isNotEmpty()) {
                helper.requestListener.forEach {
                    addListener(it)
                }
            }
            helper.thumbnailSizeMultiplier?.let { thumbnail(it) }
            if (helper.thumbnailRequestBuilder.isNotEmpty()) {
                thumbnail(*helper.thumbnailRequestBuilder.toTypedArray())
            }
            helper.transitionOptions?.let { transition(it) }
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
            if (helper.overrideWidth != null && helper.overrideHeight != null) {
                override(helper.overrideWidth, helper.overrideHeight)
            }
            helper.downsampleStrategy?.let { downsample(it) }
            helper.encodeFormat?.let { encodeFormat(it) }
            helper.encodeQuality?.let { encodeQuality(it) }
            if (helper.dontAnimate) {
                dontAnimate()
            }
            if (helper.dontTransform) {
                dontTransform()
            }
            transform(*helper.transformOptions.toTypedArray())
            if (helper.clone) {
                clone()
            }
            if (helper.autoClone) {
                autoClone()
            }
            if (helper.decodeClass != null) {
                decode(helper.decodeClass)
            }
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

    fun clone() = appliedRequestBuilder.clone()

    fun autoClone() = appliedRequestBuilder.autoClone()

    fun decode(resourceClass: Class<*>) = appliedRequestBuilder.decode(resourceClass)

}