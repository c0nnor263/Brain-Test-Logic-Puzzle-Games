package com.gamovation.core.ui.advertising

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.DialogState

@Composable
fun WatchAdErrorDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onDismissed: () -> Unit
) {
    ChalkBoardDialog(
        modifier = modifier,
        dialogState = dialogState,
        onDismissRequest = { onDismissed() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.something_went_wrong),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Yellow),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            ScalableButton(
                textStyle = MaterialTheme.typography.displaySmall,
                stringRes = R.string.ok,
                onClick = onDismissed
            )
        }
    }
}
