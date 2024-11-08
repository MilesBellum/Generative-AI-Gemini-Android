package com.eagb.generative_ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagb.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AiViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<String>> =
        MutableStateFlow(UiState.Idle)
    val uiState: StateFlow<UiState<String>> =
        _uiState.asStateFlow()

    fun aiStreaming(prompt: String) {
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val outputContent = configureModel().generateContent(prompt).text
                _uiState.value = UiState.Success(outputContent)
            } catch (e: Exception) {
                _uiState.value =
                    UiState.Error(
                        e.localizedMessage ?: "Something went wrong."
                    )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = UiState.Idle
    }
}
