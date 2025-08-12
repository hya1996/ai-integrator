package com.ai.integrator.feature.dialogue.screen.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.ai.integrator.feature.dialogue.screen.message.DialogueMessageScreen
import com.ai.integrator.feature.dialogue.screen.message.DialogueMessageViewModel
import com.ai.integrator.feature.dialogue.screen.session.DialogueSessionScreen
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DialogueHomeScreen(
    modelId: Long,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DialogueMessageViewModel = koinViewModel { parametersOf(modelId) } // todo temp use delete later
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = colorScheme.primaryContainer
            ) {
                DialogueSessionScreen(
                    modelId = modelId,
                    onSessionRecordItemClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    viewModel = viewModel
                )
            }
        },
        modifier = modifier
            .fillMaxHeight()
    ) {
        DialogueMessageScreen(
            modelId = modelId,
            onBackClick = onBackClick,
            onSessionRecordClick = {
                scope.launch {
                    drawerState.open()
                }
            },
            onAddSessionClick = {
                viewModel.createNewSession()
            },
            viewModel = viewModel
        )
    }
}