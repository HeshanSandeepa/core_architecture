package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MarsPhotosRepository {
    fun getMarsPhotos(): Flow<List<MarsPhoto>>
}

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService): MarsPhotosRepository {
    override fun getMarsPhotos(): Flow<List<MarsPhoto>> =
        flow {
            val photos = marsApiService.getPhotos()
            emit(photos)
        }
}