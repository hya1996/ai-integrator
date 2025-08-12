package com.ai.integrator.feature.dialogue.screen.message

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
import com.ai.integrator.core.ui.ime.rememberImeVisibility
import com.ai.integrator.feature.dialogue.screen.message.component.bottombar.DialogueMessageBottomBar
import com.ai.integrator.feature.dialogue.screen.message.component.messagelist.DialogueMessageList
import com.ai.integrator.feature.dialogue.screen.message.component.topbar.DialogueMessageTopBar
import kotlinx.coroutines.launch

@Composable
fun DialogueMessageScreen(
    modelId: Long,
    onBackClick: () -> Unit,
    onSessionRecordClick: () -> Unit,
    onAddSessionClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DialogueMessageViewModel // todo temp use opt later
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
                DialogueMessageTopBar(
                    title = modelInfo?.simpleName ?: "",
                    subtitle = modelInfo?.intro ?: "",
                    onBackClick = onBackClick,
                    onSessionRecordClick = onSessionRecordClick,
                    onAddSessionClick = onAddSessionClick
                )
                DialogueMessageList(
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
        DialogueMessageBottomBar(
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