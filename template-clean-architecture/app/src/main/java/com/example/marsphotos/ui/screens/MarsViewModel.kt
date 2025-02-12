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

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.domain.MarsPhoto
import com.example.marsphotos.framework.Interactors
import com.example.marsphotos.interactors.GetMarsPhotos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    data object Error : MarsUiState
    data object Loading : MarsUiState
}

open class MarsViewModel(application: Application, protected val interactors: Interactors) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
//    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
//        private set

//    private val _marsUiState = MutableStateFlow<MarsUiState>(MarsUiState.Loading)
//    val marsUiState: StateFlow<MarsUiState> = _marsUiState.asStateFlow()

    var marsUiState = MutableStateFlow<MarsUiState>(MarsUiState.Loading)
        private set

//    private val _marsUiState = MutableLiveData<MarsUiState>(MarsUiState.Loading)
//    val marsUiState: LiveData<MarsUiState> = _marsUiState


    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            interactors
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
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MarsPhotosApplication
                val interactors = Interactors(GetMarsPhotos(application.marsDataRepository))
                MarsViewModel(application, interactors)
            }
        }
    }
}
