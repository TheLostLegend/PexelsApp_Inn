package com.tll.pexelsapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import androidx.core.view.isVisible
import com.tll.pexelsapp.dagger.AppComponent
import com.tll.pexelsapp.databinding.ActivityMainBinding
import com.tll.pexelsapp.presentation.base.BaseActivity
import com.tll.pexelsapp.presentation.base.BaseViewModel
import com.tll.pexelsapp.presentation.view.navigation.NavigationButton

class Singleton: Application(){
    lateinit var appComponent: AppComponent

//    override fun onCreate() {
//        super.onCreate()
//        appComponent = DaggerAppComponent.create()
//    }
}



class MainActivity : BaseActivity(
    layoutResId = R.layout.activity_main,
    navigationHostId = R.id.nav_host_fragment_activity_main
){
    override val viewModel: BaseViewModel? = null

    private lateinit var binding: ActivityMainBinding

    private var visible: Boolean = false
    private var creation: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        creation = true
        binding.navView.isVisible = visible
        initViews()
        setContentView(binding.root)
    }

    override fun hideNavigation() {
        if (!creation){
            visible = false
        }
        else binding.navView.isVisible = false
    }

    override fun showNavigation() {
        if (!creation){
            visible = true
        }
        else binding.navView.isVisible = true
    }

    private fun initViews() {
        binding.navView.setupButtons(
            NavigationButton(
                context = this,
                iconRes = R.drawable.ic_home,
                onClickAction = { navigateTo(R.id.navigation_home) }
            ),
            NavigationButton(
                context = this,
                iconRes = R.drawable.ic_bookmarks_24dp,
                onClickAction = { navigateTo(R.id.navigation_bookmarks) }
            )
        )
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }
    }
}