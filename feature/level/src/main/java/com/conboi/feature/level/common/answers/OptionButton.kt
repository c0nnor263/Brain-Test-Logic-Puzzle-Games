package com.conboi.feature.level.common.answers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R

@Composable
internal fun OptionButton(
    modifier: Modifier = Modifier,
    index: Int,
    text: String,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = Dimensions.RoundedShape.Medium.value
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.button),
                contentDescription = stringResource(
                    id = R.string.option_button_content_description, index
                ),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = text,
                style = style.copy(color = MaterialTheme.colorScheme.onPrimary),
            )
        }

    }
}