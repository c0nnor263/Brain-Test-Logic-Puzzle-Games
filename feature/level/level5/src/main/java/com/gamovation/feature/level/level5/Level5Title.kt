package com.gamovation.feature.level.level5

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation

@Composable
internal fun Level5Title(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DrawAnimation(delayOrder = 4) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(com.gamovation.feature.level.level5.R.string.l5_opponent),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.l5_o_mark),
                    contentDescription = null
                )
            }
        }
        DrawAnimation(delayOrder = 5) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(
                        com.gamovation.feature.level.level5.R.string.l5_player_you
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.l5_x_mark),
                    contentDescription = null
                )
            }
        }
    }
}
