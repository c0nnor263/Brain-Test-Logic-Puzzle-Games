package com.gamovation.feature.store

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.core.ui.theme.boardBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreenErrorDialog(modifier: Modifier = Modifier, visible: Boolean, onDismiss: () -> Unit) {
    AnimatedVisibility(
        modifier = modifier
            .fillMaxSize(),
        visible = visible
    ) {
        AlertDialog(
            modifier = Modifier.wrapContentSize(),
            onDismissRequest = onDismiss
        ) {
            Card(
                modifier = Modifier.wrapContentSize(),
                colors = CardDefaults.cardColors(boardBackgroundColor)
            ) {
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

                    DrawAnimation(delayOrder = 1) {
                        Text(
                            text = stringResource(com.gamovation.core.ui.R.string.ok),
                            modifier = Modifier.clickableNoRipple {
                                onDismiss()
                            },
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}
