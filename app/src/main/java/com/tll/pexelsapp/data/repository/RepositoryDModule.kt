package com.tll.pexelsapp.data.repository

import com.tll.pexelsapp.data.datasource.DsModule
import dagger.Module


@Module(includes = [DsModule::class])
object RepModule
