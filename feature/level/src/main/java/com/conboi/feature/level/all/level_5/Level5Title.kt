package com.conboi.feature.level.all.level_5

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
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation

@Composable
fun Level5Title(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DrawAnimation(delay = Durations.Medium.time.toLong() * 4) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "John:", style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.o_mark),
                    contentDescription = null
                )
            }
        }
        DrawAnimation(delay = Durations.Medium.time.toLong() * 5) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "You:", style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.x_mark),
                    contentDescription = null
                )
            }
        }
    }
}