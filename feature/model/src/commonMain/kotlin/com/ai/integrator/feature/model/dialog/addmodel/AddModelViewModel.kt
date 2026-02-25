package com.ai.integrator.feature.model.dialog.addmodel

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.model.model.ModelInfo
import com.ai.integrator.data.model.repository.ModelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class AddModelViewModel(
    private val modelRepo: ModelRepository
) : BaseViewModel() {
    private val modelName = MutableStateFlow("")

    private val requestUrl = MutableStateFlow("")

    private val apiKey = MutableStateFlow("")

    val enableAddModel: StateFlow<Boolean> = combine(
        modelName,
        requestUrl,
        apiKey
    ) { name, url, key ->
        name.isNotEmpty() && url.isNotEmpty() && key.isNotEmpty()
    }.asState(viewModelScope, false)

    fun updateModelName(modelName: String) {
        this.modelName.value = modelName
    }

    fun updateRequestUrl(requestUrl: String) {
        this.requestUrl.value = requestUrl
    }

    fun updateApiKey(apiKey: String) {
        this.apiKey.value = apiKey
    }

    fun addModel() {
        val name = modelName.value
        val url = requestUrl.value
        val key = apiKey.value
        if (name.isEmpty() || url.isEmpty() || key.isEmpty()) {
            Log.e(TAG, "args are invalid. modelName: $name, requestUrl: $url, apiKey: $key")
            return
        }

        viewModelScope.launch {
            val modelInfo = ModelInfo(
                modelName = name,
                requestUrl = url,
                apiKey = key
            )
            modelRepo.addModel(modelInfo)
        }
    }

    companion object {
        private const val TAG = "AddModelViewModel"
    }
}