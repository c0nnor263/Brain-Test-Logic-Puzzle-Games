package com.conboi.feature.level.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.conboi.core.ui.R

@Composable
fun LevelActionsBar(
    modifier: Modifier = Modifier,
    onRestart: () -> Unit,
    onGetAdvice: () -> Unit,
    onSkip: () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(onClick = onRestart) {
            Icon(
                painter = painterResource(id = R.drawable.restart_icon),
                contentDescription = null
            )
        }

        IconButton(onClick = onGetAdvice) {
            Image(
                painter = painterResource(id = R.drawable.lamp),
                contentDescription = null
            )
        }

        IconButton(onClick = onSkip) {
            Icon(
                painter = painterResource(id = R.drawable.skip_icon),
                contentDescription = null
            )
        }
    }
}