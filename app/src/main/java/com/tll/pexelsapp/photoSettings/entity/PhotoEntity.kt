package com.tll.pexelsapp.photoSettings.entity

data class PhotoEntity(
    val height: Int ,
    val id: Int ,
    val photographer: String ,
    val photographerId: Int ,
    val photographerUrl: String ,
    val src: PhotoSrcEntity ,
    val url: String ,
    val width: Int
)
