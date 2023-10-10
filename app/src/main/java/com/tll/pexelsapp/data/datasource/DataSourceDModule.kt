package com.tll.pexelsapp.data.datasource

import com.tll.pexelsapp.data.db.DbModule
import com.tll.pexelsapp.data.network.NetworkModule
import dagger.Module

@Module(includes = [DbModule::class, NetworkModule::class])
object DsModule
