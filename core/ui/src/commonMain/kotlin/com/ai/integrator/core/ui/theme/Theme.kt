package com.ai.integrator.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val defaultLightColorScheme = lightColorScheme(
    primary = Color(0xFF000000), // txt1
    primaryContainer = Color(0xFFFFFFFF), // bg1
    onPrimaryContainer = Color(0xFFEAEAEA), // bg1_prs
    secondary = Color(0xB3000000), // txt2
    secondaryContainer = Color(0xFFFFFFFF), // bg4
    onSecondaryContainer = Color(0xFFEAEAEA), // bg4_prs
    tertiary = Color(0x6B000000), // txt3
    tertiaryContainer = Color(0x0A000000), // bg3
    background = Color(0xFFF6F6F6), // bg2
    outline = Color(0x14000000), // line1
)

val defaultDarkColorScheme = darkColorScheme(
    primary = Color(0xE6FFFFFF),
    primaryContainer = Color(0xFF18181D),
    onPrimaryContainer = Color(0xFF383842),
    secondary = Color(0xBDEDF3FF),
    secondaryContainer = Color(0xFF292930),
    onSecondaryContainer = Color(0xFF40404C),
    tertiary = Color(0x75EDF3FF),
    tertiaryContainer = Color(0x14EDF3FF),
    background = Color(0xFF0C0C0E),
    outline = Color(0x1Affffff),
)

@Composable
fun AITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) defaultDarkColorScheme else defaultLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}