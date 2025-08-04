package com.ai.integrator.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ai.integrator.feature.dialogue.navigation.PlatformModelRoute
import com.ai.integrator.feature.dialogue.navigation.dialogueNavGraph
import com.ai.integrator.feature.dialogue.navigation.navigateToDialogueDetail
import com.ai.integrator.feature.dialogue.navigation.platformNavGraph

@Composable
fun AINavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = PlatformModelRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        platformNavGraph(
            onModelItemClick = { modelId ->
                navController.navigateToDialogueDetail(modelId)
            }
        )
        dialogueNavGraph(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}