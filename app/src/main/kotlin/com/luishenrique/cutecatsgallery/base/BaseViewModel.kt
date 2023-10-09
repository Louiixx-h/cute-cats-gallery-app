package com.luishenrique.cutecatsgallery.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {
    protected fun <T> request(
        block: suspend () -> T,
        onStartRequest: () -> Unit = {},
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onFinally: () -> Unit = {}
    ) : Job {
        return viewModelScope.launch {
            onStartRequest.invoke()
            runCatching {
                val response = withContext(Dispatchers.IO) { block.invoke() }
                onSuccess.invoke(response)
            }.onFailure {
                onError.invoke(it)
            }
            onFinally.invoke()
        }
    }
}