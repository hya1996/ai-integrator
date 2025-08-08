package com.ai.integrator.feature.dialogue.screen.message.component.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.integrator.core.ui.theme.AITheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogueMessageBottomBar(
    inputContent: String,
    onInputChange: (String) -> Unit,
    onInputFocusChange: (Boolean) -> Unit,
    enableSend: Boolean,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = colorScheme.primaryContainer
            )
    ) {
        HorizontalDivider(
            modifier = Modifier.align(Alignment.TopCenter),
            thickness = 0.5.dp,
            color = colorScheme.outline,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            DialogueMessageInputField(
                inputContent = inputContent,
                onInputChange = onInputChange,
                onInputFocusChange = onInputFocusChange,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            DialogueMessageSendButton(
                enableSend = enableSend,
                onSendClick = onSendClick,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}

@Composable
fun DialogueMessageInputField(
    inputContent: String,
    onInputChange: (String) -> Unit,
    onInputFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = inputContent,
        onValueChange = onInputChange,
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
            .background(
                color = colorScheme.primaryContainer,
                shape = RoundedCornerShape(6.dp),
            )
            .border(
                width = 2.dp,
                color = colorScheme.outlineVariant,
                shape = RoundedCornerShape(6.dp),
            )
            .padding(
                horizontal = 8.dp,
                vertical = 10.dp
            )
            .onFocusChanged { onInputFocusChange(it.isFocused) },
        textStyle = TextStyle(
            color = colorScheme.primary,
            fontSize = 16.sp,
        ),
        maxLines = 6,
    )
}

@Composable
fun DialogueMessageSendButton(
    enableSend: Boolean,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        imageVector = if (enableSend)
            Icons.AutoMirrored.Rounded.Send else Icons.AutoMirrored.Outlined.Send,
        contentDescription = null,
        modifier = modifier
            .padding(
                start = 14.dp,
                bottom = 3.dp
            )
            .width(50.dp)
            .height(34.dp)
            .border(
                width = 2.dp,
                color = colorScheme.primary,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(
                enabled = enableSend,
                onClick = onSendClick
            )
    )
}

@Preview
@Composable
fun DialogueMessageBottomBarPreview() {
    AITheme {
        DialogueMessageBottomBar(
            inputContent = "这是一段消息",
            onInputChange = {},
            onInputFocusChange = {},
            enableSend = true,
            onSendClick = {},
        )
    }
}