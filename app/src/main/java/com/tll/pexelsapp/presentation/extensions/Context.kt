package com.tll.pexelsapp.presentation.extensions

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.tll.pexelsapp.Singleton
import com.tll.pexelsapp.dagger.AppComponent

/**
 * Getting clean pixels from Density-independent Pixels
 *
 * @property dpValue - size in Density-independent Pixels
 */
fun Context.dpToPix(dpValue: Int): Float {
    return dpValue * this.resources.displayMetrics.density
}

/**
 * Getting Density-independent Pixels from clean pixels
 *
 * @property pixValue - size in clean pixels
 */
fun Context.pixToDp(pixValue: Int): Int {
    val density = this.resources
        .displayMetrics
        .density
    return (pixValue / density).toInt()
}

/**
 * Getting device screen width in pixels
 */
fun Context.getScreenWidthInPixels(): Int {
    val metrics = DisplayMetrics()
    this.windowManager().defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

/**
 * Getting device screen height in pixels
 */
fun Context.getScreenHeightInPixels(): Int {
    val metrics = DisplayMetrics()
    this.windowManager().defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

fun Context.windowManager(): WindowManager {
    return this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
}

val Context.appComponent: AppComponent
    get() = when (this){
        is Singleton -> appComponent
        else -> this.applicationContext.appComponent
    }
