package com.gamovation.core.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.gamovation.core.ui.state.DialogState

@Composable
fun ChalkBoardDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    dialogState: DialogState,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = dialogState.isShowing
    ) {
        Dialog(onDismissRequest = onDismissRequest) {
            ChalkBoardCard {
                content()
            }
        }
    }
}
