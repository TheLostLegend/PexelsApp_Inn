package com.tll.pexelsapp.photoSettings.usecase


import com.tll.pexelsapp.data.repository.Repository
import com.tll.pexelsapp.photoSettings.entity.PhotoFavoriteEntity
import com.tll.pexelsapp.photoSettings.mapper.PhotoConverter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class PhotoFavoritesUseCase(
    private val repository: Repository
) {

    suspend fun getFavoritePhotos(): List<PhotoFavoriteEntity> {
        return withContext(IO) {
            repository.getAllFavoritePhotos().map { PhotoConverter.photoDbToPFav(it) }
        }
    }

    suspend fun addPhotoToFavorites(photo: PhotoFavoriteEntity) {
        withContext(IO) {
            repository.addPhotoToFavorites(PhotoConverter.pFavToPhotoDb(photo))
        }
    }

    suspend fun removePhotoFromFavorites(photo: PhotoFavoriteEntity) {
        withContext(IO) {
            repository.removePhotoFromFavorites(PhotoConverter.pFavToPhotoDb(photo))
        }
    }

    suspend fun checkIfPhotoInFavorites(id: Int): Boolean {
        return withContext(IO) {
            repository.checkIfPhotoInFavorites(id)
        }
    }
}
