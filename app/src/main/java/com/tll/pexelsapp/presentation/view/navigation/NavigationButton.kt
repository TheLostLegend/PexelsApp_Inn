package com.tll.pexelsapp.presentation.view.navigation

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.tll.pexelsapp.R
import com.tll.pexelsapp.databinding.ViewNavigationButtonBinding.bind
import com.tll.pexelsapp.presentation.extensions.measureWidth

class NavigationButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @DrawableRes iconRes: Int? = null,
    var onClickAction: (() -> Unit)? = null
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleWidth: Int by lazy { bind(this).btnTitleTv.measureWidth() }

    init {
        View.inflate(context, R.layout.view_navigation_button, this)
        iconRes?.let { bind(this).btnBgIv.setImageResource(it) }
    }

    fun swapIn() {
        bind(this).btnBgIv.animate()
            .alpha(0f)
            .setDuration(DEFAULT_ALPHA_ANIM_DURATION)
            .start()
        bind(this).btnTitleTv.swapInBySize(animDuration = DEFAULT_SCALE_ANIM_DURATION)
    }

    fun swapOut() {
        bind(this).btnBgIv.animate()
            .alpha(1f)
            .setDuration(DEFAULT_ALPHA_ANIM_DURATION)
            .start()
        bind(this).btnTitleTv.swapOutBySize(
            animDuration = DEFAULT_SCALE_ANIM_DURATION ,
            swapWidth = titleWidth
        )
    }

    private fun View.swapInBySize(animDuration: Long = 200) {
        val animator = ValueAnimator.ofInt(this.measuredWidth, 0)
        animator.addUpdateListener {
            val value = animator.animatedValue as Int
            val layoutParams = this.layoutParams as ViewGroup.LayoutParams
            layoutParams.width = value
            this.layoutParams = layoutParams
        }
        animator.apply {
            duration = animDuration
            interpolator = AccelerateInterpolator()
        }
        animator.start()
    }

    private fun View.swapOutBySize(animDuration: Long = 200, swapWidth: Int = 0) {
        val animator = ValueAnimator.ofInt(0, swapWidth)
        animator.addUpdateListener {
            val value = animator.animatedValue as Int
            val layoutParams = this.layoutParams as ViewGroup.LayoutParams
            layoutParams.width = value
            this.layoutParams = layoutParams
        }
        animator.apply {
            duration = animDuration
            interpolator = AccelerateInterpolator()
        }
        animator.start()
    }

    companion object {
        private const val DEFAULT_SCALE_ANIM_DURATION = 200L
        private const val DEFAULT_ALPHA_ANIM_DURATION = 350L
    }
}
