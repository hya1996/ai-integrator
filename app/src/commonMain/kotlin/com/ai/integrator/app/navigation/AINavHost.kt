package com.ai.integrator.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ai.integrator.feature.dialogue.navigation.DialogueRoute
import com.ai.integrator.feature.dialogue.navigation.dialogueNavGraph
import com.ai.integrator.feature.dialogue.navigation.navigateToDialogueDetail

@Composable
fun AINavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = DialogueRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        dialogueNavGraph(
            onModelItemClick = { modelId ->
                navController.navigateToDialogueDetail(modelId)
            },
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}