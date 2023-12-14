package com.gamovation.feature.store.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.DialogState
import com.gamovation.feature.store.R

@Composable
fun StoreScreenErrorDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onDismiss: () -> Unit
) {
    ChalkBoardDialog(modifier = modifier, onDismissRequest = onDismiss, dialogState = dialogState) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DrawAnimation {
                Text(
                    text = stringResource(R.string.you_can_t_buy_this_item),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

            ScalableButton(
                appearOrder = 1,
                stringRes = com.gamovation.core.ui.R.string.ok,
                onClick = onDismiss,
                textStyle = MaterialTheme.typography.titleLarge
            )
        }
    }
}
