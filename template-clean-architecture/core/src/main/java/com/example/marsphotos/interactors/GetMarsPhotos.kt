package com.example.marsphotos.interactors

import com.example.marsphotos.data.MarsDataRepository

class GetMarsPhotos(private val marsDataRepository: MarsDataRepository) {
    suspend operator fun invoke() = marsDataRepository.getMarsPhotos()
}