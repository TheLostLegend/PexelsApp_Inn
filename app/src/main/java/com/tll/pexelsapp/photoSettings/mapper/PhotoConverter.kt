package com.tll.pexelsapp.photoSettings.mapper

import com.tll.pexelsapp.data.db.entity.PhotoDbEntity
import com.tll.pexelsapp.data.dto.PhotoDto
import com.tll.pexelsapp.photoSettings.entity.PhotoEntity
import com.tll.pexelsapp.photoSettings.entity.PhotoFavoriteEntity
import com.tll.pexelsapp.photoSettings.entity.PhotoSrcEntity


object PhotoConverter {

    fun mapToPhotoEntity(src: PhotoDto , byScreenResolution: String): PhotoEntity {
        return PhotoEntity(
            src.height,
            src.id,
            src.photographer,
            src.photographerId,
            src.photographerUrl,
            PhotoSrcEntity(
                src.src.landscape,
                src.src.large,
                src.src.large2x,
                src.src.medium,
                src.src.original,
                src.src.portrait,
                src.src.small,
                src.src.tiny,
                byScreenResolution
            ) ,
            src.url,
            src.width
        )
    }

    fun photoDbToPFav(photo: PhotoDbEntity): PhotoFavoriteEntity {
        return PhotoFavoriteEntity(
            photo.id,
            photo.normalPhotoUrl,
            photo.bigPhotoUrl,
            photo.byScreenResolution,
            photo.photographer,
            photo.photographerUrl,
            photo.width,
            photo.height
        )
    }

    fun pFavToPhotoDb(photo: PhotoFavoriteEntity): PhotoDbEntity {
        return PhotoDbEntity(
            photo.id,
            photo.normalPhotoUrl,
            photo.bigPhotoUrl,
            photo.byScreenResolutionUrl,
            photo.photographer,
            photo.photographerUrl,
            photo.width,
            photo.height
        )
    }
}
