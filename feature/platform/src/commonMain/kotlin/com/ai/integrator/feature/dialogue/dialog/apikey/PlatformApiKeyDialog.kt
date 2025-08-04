package com.ai.integrator.feature.dialogue.dialog.apikey

import ai_integrator.feature.platform.generated.resources.Res
import ai_integrator.feature.platform.generated.resources.feature_platform_api_key_confirm
import ai_integrator.feature.platform.generated.resources.feature_platform_api_key_error_hint
import ai_integrator.feature.platform.generated.resources.feature_platform_api_key_title
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ai.integrator.core.ui.theme.AITheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlatformApiKeyDialog(
    viewModel: PlatformApiKeyViewModel = koinViewModel()
) {
    val showErrorHint by viewModel.showErrorHint.collectAsStateWithLifecycle()

    var inputApiKey by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .background(
                    color = colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.feature_platform_api_key_title),
                    color = colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp),
                )
                BasicTextField(
                    value = inputApiKey,
                    onValueChange = { inputApiKey = it },
                    maxLines = 1,
                    textStyle = TextStyle(
                        color = colorScheme.primary,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorScheme.outlineVariant,
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(
                            vertical = 13.dp,
                            horizontal = 16.dp
                        )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { viewModel.checkApiKey(inputApiKey) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Text(
                        text = stringResource(Res.string.feature_platform_api_key_confirm),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                if (showErrorHint) {
                    Text(
                        text = stringResource(Res.string.feature_platform_api_key_error_hint),
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PlatformApiKeyDialogPreview() {
    AITheme {
        PlatformApiKeyDialog()
    }
}