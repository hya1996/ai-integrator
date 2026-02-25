package com.ai.integrator.feature.model.screen.home.component.topbar

import ai_integrator.feature.model.generated.resources.Res
import ai_integrator.feature.model.generated.resources.model_feature_model_home_title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ModelHomeTopBar(
    onAddModelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.model_feature_model_home_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.primary
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(24.dp)
                    .fillMaxHeight()
                    .clickable(
                        onClick = onAddModelClick,
                        indication = null,
                        interactionSource = null,
                    )
            )
        }
    }
}