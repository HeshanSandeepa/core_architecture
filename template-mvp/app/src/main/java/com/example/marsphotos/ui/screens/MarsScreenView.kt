package com.example.marsphotos.ui.screens

interface MarsScreenView {
    fun photosFetched(marsUiState: MarsUiState)
    fun error(marsUiState: MarsUiState)
}