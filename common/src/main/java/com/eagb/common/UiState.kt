package com.eagb.common

sealed interface UiState<out T> {
    data object Idle: UiState<Nothing>

    data object Loading: UiState<Nothing>

    data class Success<T>(
        val outputStream: T? = null
    ): UiState<T>

    data class Error<T>(
        val errorMessage: String? = null
    ): UiState<T>
}
