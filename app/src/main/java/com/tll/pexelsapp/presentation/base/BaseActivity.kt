package com.tll.pexelsapp.presentation.base

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import java.io.Serializable

abstract class BaseActivity(
    @LayoutRes private val layoutResId: Int,
    @IdRes private val navigationHostId: Int? = null
) : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
        setContentView(layoutResId)
        } catch (e:Exception) {
            Log.e(TAG, "onCreateView", e);
            //throw e;
        }

        initListeners()
        initViewModelObserving()

    }

    open fun hideNavigation() {}
    open fun showNavigation() {}
    protected open fun initListeners() {}
    protected open fun initViewModelObserving() {}

    protected fun navigateTo(
        @IdRes destinationId: Int,
        navigationArgs: List<Pair<String, Serializable>>? = null
    ) {
        navigationHostId?.let { hostid ->
            navigationArgs?.let { args ->
                val bundle = Bundle()
                args.forEach { bundle.putSerializable(it.first, it.second) }
                Navigation.findNavController(this, hostid).navigate(destinationId, bundle)
            } ?: run {
                Navigation.findNavController(this, hostid).navigate(destinationId)
            }
        }
    }
}
