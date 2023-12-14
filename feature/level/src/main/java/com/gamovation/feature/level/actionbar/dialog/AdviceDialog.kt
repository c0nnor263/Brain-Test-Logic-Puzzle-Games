package com.gamovation.feature.level.actionbar.dialog

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
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.DialogState

@Composable
internal fun AdviceDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    advice: String,
    onDismiss: () -> Unit
) {
    ChalkBoardDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        dialogState = dialogState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DrawAnimation {
                Text(
                    text = advice,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

            ScalableButton(
                appearOrder = 1,
                stringRes = R.string.thanks,
                textStyle = MaterialTheme.typography.displaySmall,
                onClick = onDismiss
            )
        }
    }
}
