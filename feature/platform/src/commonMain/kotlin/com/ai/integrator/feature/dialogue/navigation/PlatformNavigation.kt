package com.ai.integrator.feature.dialogue.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ai.integrator.feature.dialogue.screen.model.PlatformModelScreen
import kotlinx.serialization.Serializable

@Serializable
data object PlatformRoute

@Serializable
data object PlatformModelRoute

fun NavController.navigateToPlatformModel() {
    navigate(route = PlatformModelRoute)
}

fun NavGraphBuilder.platformNavGraph(
    onModelItemClick: (Long) -> Unit,
) {
    composable<PlatformModelRoute> {
        PlatformModelScreen(
            onModelItemClick = onModelItemClick
        )
    }
}