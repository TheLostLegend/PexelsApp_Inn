package com.tll.pexelsapp.photoSettings.usecase

import com.tll.pexelsapp.data.consts.PhotoCategory
import com.tll.pexelsapp.photoSettings.model.CategoryModel
import com.tll.pexelsapp.data.repository.Repository
import com.tll.pexelsapp.photoSettings.entity.PhotoEntity
import com.tll.pexelsapp.photoSettings.mapper.PhotoConverter
import com.tll.pexelsapp.photoSettings.tools.PhotoUrlGenerator
import com.tll.pexelsapp.photoSettings.tools.ResolutionManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoDisplayingUseCase @Inject constructor(
    private val repository: Repository ,
    private val resolutionManager: ResolutionManager
) {

    private val photoUrlGenerator = PhotoUrlGenerator()

    suspend fun getPhotosForCategory(
        category: String,
        pageNumber: Int,
        perPageCount: Int
    ): List<PhotoEntity>? {
        return withContext(IO) {
            repository.searchPhotos(category, pageNumber, perPageCount)?.let {
                it.photos.map { photoDto ->
                    val byScreenResolution = photoUrlGenerator.generateUrl(photoDto.src.large, resolutionManager.screenResolution)
                    PhotoConverter.mapToPhotoEntity(photoDto, byScreenResolution)
                }
            }
        }
    }

    suspend fun getPhotoCategoryCover(category: PhotoCategory): CategoryModel? {
        return withContext(IO) {
            repository.searchPhotos(category.name, 1, 30)?.let {
                CategoryModel(category, it.photos.random().src.large)
            }
        }
    }

    suspend fun getCuratedPhotos(pageNumber: Int, perPageCount: Int): List<PhotoEntity>? {
        return withContext(IO) {
            repository.getCuratedPhotos(pageNumber, perPageCount)?.let {
                it.photos.map { photoDto ->
                    val byScreenResolution = photoUrlGenerator.generateUrl(photoDto.src.large, resolutionManager.screenResolution)
                    PhotoConverter.mapToPhotoEntity(photoDto, byScreenResolution)
                }
            }
        }
    }

    suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        perPageCount: Int
    ): List<PhotoEntity>? {
        return withContext(IO) {
            repository.searchPhotos(query, pageNumber, perPageCount)?.let {
                it.photos.map { photoDto ->
                    val byScreenResolution = photoUrlGenerator.generateUrl(photoDto.src.large, resolutionManager.screenResolution)
                    PhotoConverter.mapToPhotoEntity(photoDto, byScreenResolution)
                }
            }
        }
    }
}
