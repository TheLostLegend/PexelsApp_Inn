package com.tll.pexelsapp.data.datasource.remote

import com.tll.pexelsapp.data.dto.SearchResultDto
import com.tll.pexelsapp.data.network.PexelsApi

class RemoteDataSource(private val api: PexelsApi) {

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): SearchResultDto? {
        val call = api.searchPhotoByQueryCall(query, page, perPage)
        val response = call.execute()
        return response.body()
    }

    suspend fun getCuratedPhotos(page: Int, perPage: Int): SearchResultDto? {
        val call = api.getCuratedPhotos(page, perPage)
        val response = call.execute()
        return response.body()
    }
}
