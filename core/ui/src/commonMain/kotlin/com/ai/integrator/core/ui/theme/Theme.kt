package com.ai.integrator.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val defaultLightColorScheme = lightColorScheme(
    primary = Color(0xFF1A1A1A),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFF5F5F5),
    onPrimaryContainer = Color(0xFF0A0A0A),
    secondary = Color(0xFF4A4A4A),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8E8E8),
    onSecondaryContainer = Color(0xFF1A1A1A),
    tertiary = Color(0xFF6A6A6A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFEEEEEE),
    onTertiaryContainer = Color(0xFF2A2A2A),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0A0A0A),
    outline = Color(0xFFD4D4D4),
)

val defaultDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF0A0A0A),
    primaryContainer = Color(0xFF2A2A2A),
    onPrimaryContainer = Color(0xFFFAFAFA),
    secondary = Color(0xFFD4D4D4),
    onSecondary = Color(0xFF0A0A0A),
    secondaryContainer = Color(0xFF3A3A3A),
    onSecondaryContainer = Color(0xFFE8E8E8),
    tertiary = Color(0xFFA8A8A8),
    onTertiary = Color(0xFF0A0A0A),
    tertiaryContainer = Color(0xFF4A4A4A),
    onTertiaryContainer = Color(0xFFD4D4D4),
    background = Color(0xFF0A0A0A),
    onBackground = Color(0xFFFAFAFA),
    outline = Color(0xFF3A3A3A),
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
