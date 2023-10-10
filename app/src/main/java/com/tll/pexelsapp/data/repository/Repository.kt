package com.tll.pexelsapp.data.repository

import com.tll.pexelsapp.data.datasource.local.LocalDataSource
import com.tll.pexelsapp.data.datasource.remote.RemoteDataSource
import com.tll.pexelsapp.data.db.entity.PhotoDbEntity
import com.tll.pexelsapp.data.dto.SearchResultDto
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getCuratedPhotos(page: Int, perPage: Int): SearchResultDto? {
        return remoteDataSource.getCuratedPhotos(page, perPage)
    }

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): SearchResultDto? {
        return remoteDataSource.searchPhotos(query, page, perPage)
    }

    suspend fun addPhotoToFavorites(photoEntity: PhotoDbEntity) {
        localDataSource.addPhotoToFavorites(photoEntity)
    }

    suspend fun removePhotoFromFavorites(photoEntity: PhotoDbEntity) {
        localDataSource.removePhotoFromFavorites(photoEntity)
    }

    suspend fun checkIfPhotoInFavorites(id: Int): Boolean {
        return localDataSource.checkIfPhotoInFavorites(id)
    }

    suspend fun getPhotoById(id: Int): PhotoDbEntity {
        return localDataSource.getPhotoById(id)
    }

    suspend fun getAllFavoritePhotos(): List<PhotoDbEntity> {
        return localDataSource.getAllPhotos()
    }
}
