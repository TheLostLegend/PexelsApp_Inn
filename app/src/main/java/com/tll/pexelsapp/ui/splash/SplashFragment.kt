package com.tll.pexelsapp.ui.splash

import android.os.Handler
import android.view.View
import com.tll.pexelsapp.R
import com.tll.pexelsapp.presentation.base.BaseFragment
import com.tll.pexelsapp.presentation.base.BaseViewModel
import com.tll.pexelsapp.presentation.view.PlaceholderView

class SplashFragment() : BaseFragment(
    layoutResId = R.layout.fragment_splash,
    hideNavigation = true
) {
    override val viewModel: BaseViewModel? = null
    override val toolbarTitle: String? = null
    override val contentView: View? = null
    override val placeholderView: PlaceholderView? = null

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({ navigateTo(R.id.navigation_home) }, 2000)
    }
}
