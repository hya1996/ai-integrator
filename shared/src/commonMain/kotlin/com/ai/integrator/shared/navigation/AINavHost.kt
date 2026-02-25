package com.ai.integrator.shared.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ai.integrator.feature.model.navigation.ModelHomeRoute
import com.ai.integrator.feature.dialogue.navigation.dialogueNavGraph
import com.ai.integrator.feature.dialogue.navigation.navigateToDialogueHome
import com.ai.integrator.feature.model.navigation.modelNavGraph

@Composable
fun AINavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ModelHomeRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        modelNavGraph(
            onModelItemClick = { modelId ->
                navController.navigateToDialogueHome(modelId)
            }
        )
        dialogueNavGraph(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}