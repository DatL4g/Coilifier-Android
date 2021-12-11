package de.datlag.coilifier

import android.view.View

data class PlaceholderScaling internal constructor(
	internal val size: Int?,
	internal val max: Boolean,
	internal val scaleByWidth: Boolean?
) {
	companion object {
		fun fitCenter() = PlaceholderScaling(null, true, null)
		fun fitCenter(size: Int?, byWidth: Boolean? = null) = PlaceholderScaling(size, true, byWidth)
		fun fitCenter(view: View?, byWidth: Boolean? = null): PlaceholderScaling {
			return if (view != null) {
				fitCenter(viewSize(view, byWidth), byWidth)
			} else {
				fitCenter()
			}
		}
		
		private fun sizeValid(value: Int?): Boolean {
			return value == null || value <= 0
		}
		
		private fun viewSize(view: View, byWidth: Boolean?): Int? {
			val widthSize = when {
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

			val heightSize = when {
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

			return if (byWidth == true) {
				widthSize
			} else if (byWidth == false) {
				heightSize
			} else {
				if (widthSize != null && heightSize != null) {
					if (widthSize > heightSize) {
						widthSize
					} else {
						heightSize
					}
				} else if (widthSize != null && heightSize == null) {
					widthSize
				} else if (widthSize == null && heightSize != null) {
					heightSize
				} else {
					null
				}
			}
		}
	}
}