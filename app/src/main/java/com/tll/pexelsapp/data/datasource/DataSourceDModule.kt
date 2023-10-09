package com.tll.pexelsapp.data.datasource

import com.tll.pexelsapp.data.datasource.local.LocalDataSource
import com.tll.pexelsapp.data.datasource.remote.RemoteDataSource
import com.tll.pexelsapp.data.db.AppDatabase
import com.tll.pexelsapp.data.db.DbModule
import com.tll.pexelsapp.data.network.PexelsApi
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
@Component(modules = [DbModule::class])
object DsModule{

    @Provides
    fun getRemoteDS(api: PexelsApi): RemoteDataSource {
        return RemoteDataSource(api)
    }
    @Provides
    fun getLocalDS(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSource(appDatabase)
    }



}
