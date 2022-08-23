package com.example.multiplatform.koru.shared.viewmodel

import com.futuremind.koru.ToNativeClass
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.multiplatform.koru.shared.scope.MainScopeProvider

@ToNativeClass(
    name = "CountdownViewModelIos",
    launchOnScope = MainScopeProvider::class
)
class CountdownViewModel : ViewModel() {

    val countdown: MutableStateFlow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch {
            (100 downTo 1).forEach {
                countdown.value = "...$it..."
                delay(1000)
            }
            countdown.value = "Finished countdown"
        }
    }

    override protected fun onCleared() {
        println("onCleared")
    }

}