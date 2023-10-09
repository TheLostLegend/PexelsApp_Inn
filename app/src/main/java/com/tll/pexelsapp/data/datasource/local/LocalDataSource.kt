package com.tll.pexelsapp.data.datasource.local


import com.tll.pexelsapp.data.db.AppDatabase
import com.tll.pexelsapp.data.db.entity.PhotoDbEntity

class LocalDataSource(private val appDatabase: AppDatabase) {

    suspend fun addPhotoToFavorites(photoEntity: PhotoDbEntity) {
        appDatabase.photoDao().insert(photoEntity)
    }

    suspend fun removePhotoFromFavorites(photoEntity: PhotoDbEntity) {
        appDatabase.photoDao().delete(photoEntity)
    }

    suspend fun checkIfPhotoInFavorites(id: Int): Boolean {
        return appDatabase.photoDao().getById(id).isNotEmpty()
    }

    suspend fun getPhotoById(id: Int): PhotoDbEntity {
        return appDatabase.photoDao().getById(id).first()
    }

    suspend fun getAllPhotos(): List<PhotoDbEntity> {
        return appDatabase.photoDao().getAll()
    }
}
