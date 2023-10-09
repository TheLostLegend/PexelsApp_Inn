package com.tll.pexelsapp.dagger

import com.tll.pexelsapp.photoSettings.usecase.UseCaseModule
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface AppComponent

