package com.example.multiplatform.koru.shared.viewmodel

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive

actual abstract class ViewModel {
    actual val viewModelScope = MainScope()

    protected actual open fun onCleared() {
        println("Deep ios ViewModel onCleared")
    }

    fun clear() {
        onCleared()
        viewModelScope.cancel()
        println("ViewModelScope is active: ${viewModelScope.isActive}")
    }
}