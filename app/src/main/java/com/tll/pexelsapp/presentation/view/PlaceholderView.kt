package com.tll.pexelsapp.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.tll.pexelsapp.R
import com.tll.pexelsapp.databinding.ViewPlaceholderBinding.bind

class PlaceholderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var onTryNowBtnClickAction: (() -> Unit)? = null
        set(value) {
            bind(this).tryNowBtn.isVisible = value != null
            value?.let { action ->
                bind(this).tryNowBtn.setOnClickListener { action.invoke() }
            }
            field = value
        }

    init {
        inflate(context, R.layout.view_placeholder, this)
        bind(this).tryNowBtn.setOnClickListener { hideAll() }
    }

    fun setState(state: PlaceholderState) {
        when (state) {
            PlaceholderState.LOADING -> showLoading()
            PlaceholderState.ERROR -> showError()
            PlaceholderState.INITIAL -> showInitial()
            PlaceholderState.EMPTY -> showEmpty()
            PlaceholderState.GONE -> hideAll()
        }
    }

    private fun showLoading() {
        show()
        bind(this).loadingContainerLl.isVisible = true
        bind(this).errorContainerLl.isVisible = false
        bind(this).initialContainerLl.isVisible = false
        bind(this).emptyContainerLl.isVisible = false
    }
    private fun showError() {
        show()
        bind(this).loadingContainerLl.isVisible = false
        bind(this).errorContainerLl.isVisible = true
        bind(this).initialContainerLl.isVisible = false
        bind(this).emptyContainerLl.isVisible = false
    }
    private fun showEmpty() {
        show()
        bind(this).loadingContainerLl.isVisible = false
        bind(this).errorContainerLl.isVisible = false
        bind(this).initialContainerLl.isVisible = false
        bind(this).emptyContainerLl.isVisible = true
    }
    private fun showInitial() {
        show()
        bind(this).loadingContainerLl.isVisible = false
        bind(this).errorContainerLl.isVisible = false
        bind(this).initialContainerLl.isVisible = true
        bind(this).emptyContainerLl.isVisible = false
    }
    private fun hideAll() {
        bind(this).loadingContainerLl.isVisible = false
        bind(this).errorContainerLl.isVisible = false
        bind(this).initialContainerLl.isVisible = false
        bind(this).emptyContainerLl.isVisible = false
        this.isVisible = false
    }
    private fun show() {
        this.isVisible = true
    }

    enum class PlaceholderState {
        LOADING, ERROR, INITIAL, EMPTY, GONE
    }
}
