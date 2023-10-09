package com.tll.pexelsapp.photoSettings.tools

import android.content.Context
import android.util.DisplayMetrics
import com.tll.pexelsapp.presentation.extensions.windowManager

class ResolutionManager(private val context: Context) {

    val screenResolution: Resolution by lazy {
        val metrics = DisplayMetrics()
        context.windowManager().defaultDisplay.getMetrics(metrics)
        Resolution(
            metrics.widthPixels,
            metrics.heightPixels
        )
    }

    data class Resolution(
        val width: Int,
        val height: Int
    )
}
