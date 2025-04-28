package com.ai.integrator.feature.dialogue.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ai.integrator.core.ui.ime.rememberImeVisibility
import com.ai.integrator.feature.dialogue.screen.detail.component.bottombar.DialogueDetailBottomBar
import com.ai.integrator.feature.dialogue.screen.detail.component.messagelist.DialogueDetailMessageList
import com.ai.integrator.feature.dialogue.screen.detail.component.topbar.DialogueDetailTopBar
import kotlinx.coroutines.launch

@Composable
fun DialogueDetailScreen(
    modelId: Long,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DialogueDetailViewModel = viewModel(factory = DialogueDetailViewModelFactory(modelId))
) {
    val localFocusManger = LocalFocusManager.current

    val modelInfo by viewModel.modelInfo.collectAsStateWithLifecycle()
    val inputContent by viewModel.inputContent.collectAsStateWithLifecycle()
    val enableSend by viewModel.enableSend.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()

    val isImeVisible by rememberImeVisibility()
    val scope = rememberCoroutineScope()
    val msgListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.weight(1f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                DialogueDetailTopBar(
                    title = modelInfo?.simpleName ?: "",
                    subtitle = modelInfo?.intro ?: "",
                    onBackClick = onBackClick,
                )
                DialogueDetailMessageList(
                    messages = messages,
                    listState = msgListState,
                    modifier = Modifier
                        .weight(1f),
                )
            }
            if (isImeVisible) {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    localFocusManger.clearFocus()
                                }
                            )
                        }
                )
            }
        }
        DialogueDetailBottomBar(
            inputContent = inputContent,
            onInputChange = { viewModel.updateInputContent(it) },
            onInputFocusChange = { isFocused ->
                scope.launch {
                    if (isFocused) msgListState.animateScrollToItem(0)
                }
            },
            enableSend = enableSend,
            onSendClick = {
                viewModel.sendDialogueMessage()
                scope.launch {
                    msgListState.animateScrollToItem(0)
                }
            },
        )
    }
}