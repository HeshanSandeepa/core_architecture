package com.example.marsphotos.data


class MarsDataRepository(private val marsDataSource: MarsDataSource) {
    suspend fun getMarsPhotos() = marsDataSource.getMarsPhotos()
}