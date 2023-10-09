package com.tll.pexelsapp.photoSettings.tools

import android.content.Context
import com.tll.pexelsapp.data.repository.RepModule
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
@Component(modules = [RepModule::class])
object ToolsModule {
    @Provides
    fun getResolutionManager(context: Context):ResolutionManager {
        return ResolutionManager(context)
    }
}
