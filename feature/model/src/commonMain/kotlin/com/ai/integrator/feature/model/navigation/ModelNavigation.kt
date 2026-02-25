package com.ai.integrator.feature.model.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ai.integrator.feature.model.screen.home.ModelHomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object ModelRoute

@Serializable
data object ModelHomeRoute

fun NavController.navigateToModelHome() {
    navigate(route = ModelHomeRoute)
}

fun NavGraphBuilder.modelNavGraph(
    onModelItemClick: (Long) -> Unit,
) {
    composable<ModelHomeRoute> {
        ModelHomeScreen(
            onModelItemClick = onModelItemClick
        )
    }
}