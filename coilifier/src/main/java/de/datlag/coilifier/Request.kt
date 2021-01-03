package de.datlag.coilifier

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class Request<ResourceType> {
    constructor(context: Context) {
        requestManager = Glide.with(context)
        resources = context.resources
    }
    constructor(activity: Activity) {
        requestManager = Glide.with(activity)
        resources = activity.resources
    }
    constructor(fragment: Fragment) {
        requestManager = Glide.with(fragment)
        resources = fragment.activity?.resources ?: fragment.context?.resources ?: fragment.resources
    }
    constructor(view: View) {
        requestManager = Glide.with(view)
        resources = view.resources ?: view.context.resources
    }
    constructor(fragmentActivity: FragmentActivity) {
        requestManager = Glide.with(fragmentActivity)
        resources = fragmentActivity.resources
    }

    private var requestManager: RequestManager
    private var resources: Resources?

    fun `as`(type: Class<ResourceType>) = Loader(requestManager.`as`(type), resources)
    fun asDrawable() = Loader(requestManager.asDrawable(), resources)
    fun asBitmap() = Loader(requestManager.asBitmap(), resources)
    fun asFile() = Loader(requestManager.asFile(), resources)
    fun asGif() = Loader(requestManager.asGif(), resources)
}