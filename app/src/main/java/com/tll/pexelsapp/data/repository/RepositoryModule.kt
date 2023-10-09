package com.tll.pexelsapp.data.repository

import com.tll.pexelsapp.data.datasource.DsModule
import com.tll.pexelsapp.data.datasource.local.LocalDataSource
import com.tll.pexelsapp.data.datasource.remote.RemoteDataSource
import dagger.Component
import dagger.Module
import dagger.Provides


@Component(modules = [DsModule::class])
@Module
object RepModule{
    @Provides
    fun getRep(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource):Repository{
        return Repository(localDataSource, remoteDataSource)
    }
}
