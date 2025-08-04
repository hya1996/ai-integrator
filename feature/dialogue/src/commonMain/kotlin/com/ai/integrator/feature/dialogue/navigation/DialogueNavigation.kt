package com.ai.integrator.feature.dialogue.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ai.integrator.feature.dialogue.screen.detail.DialogueDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data object DialogueRoute

@Serializable
data class DialogueDetailRoute(
    val modelId: Long,
)

fun NavController.navigateToDialogueDetail(modelId: Long) {
    navigate(route = DialogueDetailRoute(modelId))
}

fun NavGraphBuilder.dialogueNavGraph(
    onBackClick: () -> Unit
) {
    composable<DialogueDetailRoute> {
        val route: DialogueDetailRoute = it.toRoute()
        DialogueDetailScreen(
            modelId = route.modelId,
            onBackClick = onBackClick,
        )
    }
}