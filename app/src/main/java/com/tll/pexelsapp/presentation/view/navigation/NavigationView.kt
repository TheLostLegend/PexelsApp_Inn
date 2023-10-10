package com.tll.pexelsapp.presentation.view.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.tll.pexelsapp.R
import com.tll.pexelsapp.databinding.ViewNavigationBinding.bind

class NavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val activeScreensManager = ActiveButtonsManager()

    init {
        View.inflate(context, R.layout.view_navigation, this)
    }

    fun setupButtons(vararg buttons: NavigationButton) {
        buttons.forEach { button ->
            button.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                1.0f
            )
            bind(this).buttonsContainerLl.addView(button)
            activeScreensManager.addButton(button)
            button.setOnClickListener {
                activeScreensManager.toggleButtonToActive(button)
                button.onClickAction?.invoke()
            }
        }
        activeScreensManager.onActiveButtonChangedAction = { activeButton, inactiveButtons ->
            activeButton.swapOut()
            inactiveButtons.forEach { it.swapIn() }
        }
        activeScreensManager.toggleButtonToActive(buttons.first())
    }
}
