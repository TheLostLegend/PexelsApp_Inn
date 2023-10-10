package com.tll.pexelsapp.presentation.view

import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.tll.pexelsapp.R
import com.tll.pexelsapp.databinding.ViewProgressBinding.bind
import com.tll.pexelsapp.presentation.extensions.addEndListener

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val animator: ViewAnimator

    init {
        inflate(context, R.layout.view_progress, this)
        animator = ViewAnimator()
        startLoadingAnimation()
    }

    private fun startLoadingAnimation() {
        val scaleFirst = AnimatorSet().apply {
            play(animator.shiftUp(bind(this@ProgressView).pLetterTv, 450))
                .with(animator.shiftDown(bind(this@ProgressView).wLetterTv, 450))
                .with(animator.fadeIn(bind(this@ProgressView).dividerV, 450))
        }
        val scaleBack = AnimatorSet().apply {
            play(animator.shiftBack(bind(this@ProgressView).wLetterTv, 450))
                .with(animator.shiftBack(bind(this@ProgressView).pLetterTv, 450))
                .with(animator.fadeOut(bind(this@ProgressView).dividerV, 450))
        }
        val scaleBackSecond = AnimatorSet().apply {
            play(animator.shiftBack(bind(this@ProgressView).pLetterTv, 450))
                .with(animator.shiftBack(bind(this@ProgressView).wLetterTv, 450))
                .with(animator.fadeOut(bind(this@ProgressView).dividerV, 450))
        }
        val scaleSecond = AnimatorSet().apply {
            play(animator.shiftUp(bind(this@ProgressView).wLetterTv, 450))
                .with(animator.shiftDown(bind(this@ProgressView).pLetterTv, 450))
                .with(animator.fadeIn(bind(this@ProgressView).dividerV, 450))
        }
        val scaleFull = AnimatorSet().apply {
            play(scaleFirst).before(scaleBack)
            play(scaleSecond).after(scaleBack)
        }
        val loadingAnimatorSet = AnimatorSet().apply {
            play(scaleFull).before(scaleBackSecond)
            addEndListener { this.start() }
        }
        loadingAnimatorSet.start()
    }

    fun show() {
        animator.show(this)
    }

    fun hide() {
        animator.hide(this)
    }
}
