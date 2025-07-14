package com.ai.integrator.feature.dialogue.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ai.integrator.feature.dialogue.screen.detail.DialogueDetailScreen
import com.ai.integrator.feature.dialogue.screen.home.DialogueHomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object DialogueRoute

@Serializable
data object DialogueHomeRoute

@Serializable
data class DialogueDetailRoute(
    val modelId: Long,
)

fun NavController.navigateToDialogueHome() {
    navigate(route = DialogueHomeRoute)
}

fun NavController.navigateToDialogueDetail(modelId: Long) {
    navigate(route = DialogueDetailRoute(modelId))
}

fun NavGraphBuilder.dialogueNavGraph(
    onModelItemClick: (Long) -> Unit,
    onBackClick: () -> Unit,
) {
    navigation<DialogueRoute>(startDestination = DialogueHomeRoute) {
        composable<DialogueHomeRoute> {
            DialogueHomeScreen(
                onModelItemClick = onModelItemClick
            )
        }
        composable<DialogueDetailRoute> {
            val route: DialogueDetailRoute = it.toRoute()
            DialogueDetailScreen(
                modelId = route.modelId,
                onBackClick = onBackClick,
            )
        }
    }
}