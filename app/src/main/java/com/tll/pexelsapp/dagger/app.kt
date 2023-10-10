package com.tll.pexelsapp.dagger

import com.tll.pexelsapp.photoSettings.usecase.UseCaseDModule
import dagger.Component

@Component(modules = [UseCaseDModule::class])
interface AppComponent

