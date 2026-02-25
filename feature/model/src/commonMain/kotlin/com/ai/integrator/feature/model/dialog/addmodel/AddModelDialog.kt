package com.ai.integrator.feature.model.dialog.addmodel

import ai_integrator.feature.model.generated.resources.Res
import ai_integrator.feature.model.generated.resources.model_feature_add_model_api_key_title
import ai_integrator.feature.model.generated.resources.model_feature_add_model_cancel
import ai_integrator.feature.model.generated.resources.model_feature_add_model_confirm
import ai_integrator.feature.model.generated.resources.model_feature_add_model_model_name_title
import ai_integrator.feature.model.generated.resources.model_feature_add_model_request_url_title
import ai_integrator.feature.model.generated.resources.model_feature_add_model_title
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddModelDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddModelViewModel = koinViewModel()
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val enableConfirm by viewModel.enableAddModel.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        sheetGesturesEnabled = false,
        dragHandle = null,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        containerColor = colorScheme.background,
        contentColor = Color.Transparent,
        scrimColor = Color.Transparent,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.model_feature_add_model_title),
                color = colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            ModelInfoInputList(
                onModelNameChange = viewModel::updateModelName,
                onRequestUrlChange = viewModel::updateRequestUrl,
                onApiKeyChange = viewModel::updateApiKey
            )
            Spacer(modifier = Modifier.height(24.dp))
            BottomBtnRow(
                enableConfirm = enableConfirm,
                onConfirm = {
                    viewModel.addModel()
                    onDismiss()
                },
                onCancel = {
                    onDismiss()
                }
            )
        }
    }
}

@Composable
private fun ModelInfoInputList(
    onModelNameChange: (String) -> Unit,
    onRequestUrlChange: (String) -> Unit,
    onApiKeyChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var modelName by remember { mutableStateOf("") }
    var requestUrl by remember { mutableStateOf("") }
    var apiKey by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ModelInfoInputItem(
            title = stringResource(Res.string.model_feature_add_model_model_name_title),
            inputValue = modelName,
            onInputValueChange = {
                modelName = it
                onModelNameChange(it)
            }
        )
        ModelInfoInputItem(
            title = stringResource(Res.string.model_feature_add_model_request_url_title),
            inputValue = requestUrl,
            onInputValueChange = {
                requestUrl= it
                onRequestUrlChange(it)
            }
        )
        ModelInfoInputItem(
            title = stringResource(Res.string.model_feature_add_model_api_key_title),
            inputValue = apiKey,
            onInputValueChange = {
                apiKey = it
                onApiKeyChange(it)
            }
        )
    }
}

@Composable
private fun ModelInfoInputItem(
    title: String,
    inputValue: String,
    onInputValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = colorScheme.primary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = modifier.height(6.dp))
        BasicTextField(
            value = inputValue,
            onValueChange = onInputValueChange,
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
                    vertical = 9.dp,
                    horizontal = 12.dp
                )
        )
    }
}

@Composable
private fun BottomBtnRow(
    enableConfirm: Boolean,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onCancel,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, colorScheme.outline),
            colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(Res.string.model_feature_add_model_cancel),
                color = colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Button(
            onClick = onConfirm,
            enabled = enableConfirm,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(
                containerColor = colorScheme.onPrimaryContainer,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(Res.string.model_feature_add_model_confirm),
                color = colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}