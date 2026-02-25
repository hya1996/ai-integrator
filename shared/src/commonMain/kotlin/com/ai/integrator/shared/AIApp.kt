package com.ai.integrator.shared

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ai.integrator.shared.navigation.AINavHost
import com.ai.integrator.core.ui.theme.AITheme

@Composable
fun AIApp(
    modifier: Modifier = Modifier
) {
    AITheme {
        AIHome(modifier)
    }
}

@Composable
fun AIHome(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = colorScheme.primaryContainer,
    ) {
        Scaffold(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing),
        ) { paddingValues ->
            AINavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Preview
@Composable
fun AIAppPreview() {
    AIApp()
}