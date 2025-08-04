package com.ai.integrator.feature.dialogue.dialog.apikey

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.common.onFailure
import com.ai.integrator.core.framework.common.onSuccess
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.platform.repository.PlatformInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlatformApiKeyViewModel(
    private val platformInfoRepo: PlatformInfoRepository
) : BaseViewModel() {
    private val _showErrorHint = MutableStateFlow(false)
    val showErrorHint = _showErrorHint

    fun checkApiKey(apiKey: String) {
        if (apiKey.isEmpty()) return

        viewModelScope.launch {
            platformInfoRepo.checkApiKeyIsValid(apiKey)
                .onSuccess {
                    _showErrorHint.value = false
                    platformInfoRepo.updateApiKey(apiKey)
                }
                .onFailure { _, _ ->
                    _showErrorHint.value = true
                }
        }
    }
}