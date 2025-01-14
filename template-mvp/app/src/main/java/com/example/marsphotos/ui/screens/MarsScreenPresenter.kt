package com.example.marsphotos.ui.screens

import com.example.marsphotos.data.MarsPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class MarsScreenPresenter( val marsScreenView: MarsScreenView,
                           val repository: MarsPhotosRepository) {

    suspend fun fetchPhotos() {
        repository
            .getMarsPhotos()
            .flowOn(Dispatchers.IO)
            .collect {
                try {
                    marsScreenView.photosFetched(MarsUiState.Success( it))
                } catch (e: IOException) {
                    e.localizedMessage?.let { marsScreenView.error(MarsUiState.Error) }
                } catch (e: HttpException) {
                    e.localizedMessage?.let { marsScreenView.error(MarsUiState.Error) }
                }
            }
    }
}