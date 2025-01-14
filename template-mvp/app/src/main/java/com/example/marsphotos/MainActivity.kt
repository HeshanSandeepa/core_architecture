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

package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.marsphotos.ui.MarsPhotosApp
import com.example.marsphotos.ui.screens.MarsScreenPresenter
import com.example.marsphotos.ui.screens.MarsScreenView
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.theme.MarsPhotosTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), MarsScreenView {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val application = application as MarsPhotosApplication
        val marsPhotosRepository = application.container.marsPhotosRepository

        val presenter = MarsScreenPresenter(this, marsPhotosRepository)

        lifecycleScope.launch {
            presenter.fetchPhotos()
        }

        setContent {
            MarsPhotosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MarsPhotosApp(MarsUiState.Loading)
                }
            }
        }
    }

    override fun photosFetched(marsUiState: MarsUiState) {
        setContent {
            MarsPhotosApp(marsUiState)
        }
    }

    override fun error(marsUiState: MarsUiState) {
        setContent {
            MarsPhotosApp(marsUiState)
        }
    }


}
