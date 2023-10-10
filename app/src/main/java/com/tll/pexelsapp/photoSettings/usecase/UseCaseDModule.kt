package com.tll.pexelsapp.photoSettings.usecase

import com.tll.pexelsapp.data.repository.RepModule
import com.tll.pexelsapp.photoSettings.tools.ToolsDModule
import dagger.Module

@Module(includes = [ToolsDModule::class, RepModule::class])
object UseCaseDModule
