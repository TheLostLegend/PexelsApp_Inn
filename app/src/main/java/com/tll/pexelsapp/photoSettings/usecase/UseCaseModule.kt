package com.tll.pexelsapp.photoSettings.usecase

import android.content.Context
import com.tll.pexelsapp.data.repository.RepModule
import com.tll.pexelsapp.data.repository.Repository
import com.tll.pexelsapp.photoSettings.tools.ResolutionManager
import com.tll.pexelsapp.photoSettings.tools.ToolsModule
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
@Component(modules = [ToolsModule::class])
object UseCaseModule {
    @Provides
    fun photoDisplay(repository: Repository, resolutionManager: ResolutionManager):PhotoDisplayingUseCase {
        return PhotoDisplayingUseCase(repository, resolutionManager)
    }
    @Provides
    fun photoDownload(context: Context, resolutionManager: ResolutionManager):PhotoDownloadingUseCase {
        return PhotoDownloadingUseCase(context, resolutionManager) }
    @Provides
    fun photoBookmark(repository: Repository): PhotoFavoritesUseCase {
        return PhotoFavoritesUseCase(repository)
    }
}
