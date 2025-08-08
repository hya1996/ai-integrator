package com.ai.integrator.feature.dialogue.screen.session.component.topbar

import ai_integrator.feature.dialogue.generated.resources.Res
import ai_integrator.feature.dialogue.generated.resources.feature_dialogue_session_record_title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.integrator.core.ui.theme.AITheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogueSessionTopBar(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(Res.string.feature_dialogue_session_record_title),
            color = colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colorScheme.outlineVariant,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun DialogueSessionTopBarPreview() {
    AITheme {
        DialogueSessionTopBar()
    }
}