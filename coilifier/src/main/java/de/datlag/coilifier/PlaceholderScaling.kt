package de.datlag.coilifier

import android.view.View

data class PlaceholderScaling internal constructor(
	internal val size: Int?,
	internal val max: Boolean,
	internal val scaleByWidth: Boolean?
) {
	companion object {
		fun fitCenter() = PlaceholderScaling(null, true, null)
		fun fitCenter(size: Int?, byWidth: Boolean = true) = PlaceholderScaling(size, true, byWidth)
		fun fitCenter(view: View?, byWidth: Boolean = true): PlaceholderScaling {
			return if (view != null) {
				fitCenter(viewSize(view, byWidth), byWidth)
			} else {
				fitCenter()
			}
		}
		
		private fun sizeValid(value: Int?): Boolean {
			return value == null || value <= 0
		}
		
		private fun viewSize(view: View, byWidth: Boolean): Int? {
			return if (byWidth) {
				when {
					sizeValid(view.width) -> {
						view.width
					}
					sizeValid(view.measuredWidth) -> {
						view.measuredWidth
					}
					sizeValid(view.minimumWidth) -> {
						view.minimumWidth
					}
					else -> {
						null
					}
				}
			} else {
				when {
					sizeValid(view.height) -> {
						view.height
					}
					sizeValid(view.measuredHeight) -> {
						view.measuredHeight
					}
					sizeValid(view.minimumHeight) -> {
						view.minimumHeight
					}
					else -> {
						null
					}
				}
			}
		}
	}
}