package com.gamovation.feature.level.actionbar.dialog.options

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.feature.level.R

@Composable
fun ActionBarDialogAdviceOption(onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DrawAnimation {
            Text(
                text = stringResource(R.string.advice_nothing_else_pops_into_your_head),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
        DrawAnimation(delayOrder = 1) {
            Text(
                text = stringResource(R.string.advise_well_you_can_get_a_hint),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Yellow),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))

        DrawAnimation(delayOrder = 2) {
            Text(
                text = stringResource(R.string.advise_get_hint),
                modifier = Modifier.clickableNoRipple { onClick() },
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
