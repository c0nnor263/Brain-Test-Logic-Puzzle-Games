package com.gamovation.feature.level.action_bar.dialog.options

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.feature.level.R

@Composable
fun ActionBarDialogSkipOption(onClick: () -> Unit) {
    DrawAnimation {
        Text(
            text = stringResource(R.string.skip_do_you_really_want_to_skip_this_level),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
    DrawAnimation(delayOrder = 1) {
        Text(
            text = stringResource(R.string.skip_this_level_will_let_you_do_this),
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Yellow),
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))

    DrawAnimation(delayOrder = 2) {
        Text(
            text = stringResource(R.string.skip),
            modifier = Modifier.clickableNoRipple { onClick() },
            style = MaterialTheme.typography.displaySmall
        )
    }
}