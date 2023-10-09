package com.tll.pexelsapp.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides

private const val DB_NAME = "pexels_db"

@Module
object DbModule{
    @Provides
    fun makeDbInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}


