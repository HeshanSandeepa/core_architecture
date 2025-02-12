package com.example.marsphotos.data

import com.example.marsphotos.domain.MarsPhoto
import kotlinx.coroutines.flow.Flow

interface MarsDataSource {
    suspend fun getMarsPhotos(): Flow<List<MarsPhoto>>
}