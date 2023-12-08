package com.gamovation.feature.level.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.domain.level.ActionResult
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.DialogState
import com.gamovation.feature.level.R

@Composable
internal fun NotEnoughCurrencyDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onActionResult: (ActionResult) -> Unit
) {
    ChalkBoardDialog(
        modifier = modifier,
        onDismissRequest = { },
        dialogState = dialogState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DrawAnimation {
                Text(
                    text = stringResource(
                        R.string.you_don_t_have_enough_currency_to_get_a_hint
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.do_you_want_to_get_more),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Yellow
                    ),
                    modifier = Modifier.weight(0.7F),
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(
                        id = com.gamovation.core.ui.R.drawable.lamp
                    ),
                    modifier = Modifier.weight(0.1F),
                    contentDescription = null
                )
                Text(
                    text = "?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Yellow
                    ),
                    modifier = Modifier.weight(0.2F),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

            ScalableButton(
                delayOrder = 1,
                onClick = {
                    onActionResult(ActionResult(ActionResult.Type.BUY_MORE))
                },
                stringRes = R.string.get_more,
                textStyle = MaterialTheme.typography.displaySmall
            )
        }
    }
}
