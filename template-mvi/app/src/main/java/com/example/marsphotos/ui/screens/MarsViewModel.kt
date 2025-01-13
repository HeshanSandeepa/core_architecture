/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotoIntent
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.repository.MarsPhotosRepository
import com.example.marsphotos.data.domain.MarsPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */

    var marsUiState = MutableStateFlow<MarsUiState>(MarsUiState.Loading)
        private set
    init {
        handleIntent(MarsPhotoIntent.LoadMarsIntent)
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun handleIntent(intent: MarsPhotoIntent) {
        viewModelScope.launch {
            when (intent) {
                is MarsPhotoIntent.LoadMarsIntent -> getMarsPhotos()
            }
        }
    }

    private suspend fun getMarsPhotos() {
        marsPhotosRepository
            .getMarsPhotos()
            .flowOn(Dispatchers.IO)
            .collect {
                marsUiState.value = try {
                    MarsUiState.Success( it)
                } catch (e: IOException) {
                    MarsUiState.Error
                } catch (e: HttpException) {
                    MarsUiState.Error
                }
            }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
