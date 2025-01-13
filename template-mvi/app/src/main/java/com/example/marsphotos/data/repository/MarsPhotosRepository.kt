package com.example.marsphotos.data.repository

import com.example.marsphotos.data.domain.MarsPhoto
import com.example.marsphotos.network.MarsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MarsPhotosRepository {
    fun getMarsPhotos(): Flow<List<MarsPhoto>>
}

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService):
    MarsPhotosRepository {
    override fun getMarsPhotos(): Flow<List<MarsPhoto>> =
        flow {
            val photos = marsApiService.getPhotos()
            emit(photos)
        }
}