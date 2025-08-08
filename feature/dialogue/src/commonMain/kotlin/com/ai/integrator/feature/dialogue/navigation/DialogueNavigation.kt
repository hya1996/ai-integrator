package com.ai.integrator.feature.dialogue.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ai.integrator.feature.dialogue.screen.home.DialogueHomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object DialogueRoute

@Serializable
data class DialogueHomeRoute(
    val modelId: Long,
)

fun NavController.navigateToDialogueHome(modelId: Long) {
    navigate(route = DialogueHomeRoute(modelId))
}

fun NavGraphBuilder.dialogueNavGraph(
    onBackClick: () -> Unit
) {
    composable<DialogueHomeRoute> {
        val route: DialogueHomeRoute = it.toRoute()
        DialogueHomeScreen(
            modelId = route.modelId,
            onBackClick = onBackClick,
        )
    }
}