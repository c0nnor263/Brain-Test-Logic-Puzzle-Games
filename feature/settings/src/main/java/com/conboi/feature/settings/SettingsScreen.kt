package com.conboi.feature.settings

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.theme.WordefullTheme

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Notification", style = MaterialTheme.typography.headlineMedium)
            Box(contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.matchParentSize(),
                    painter = painterResource(id = R.drawable.l5_o_mark),
                    contentDescription = null
                )
                IconButton(
                    modifier = Modifier.padding(Dimensions.Padding.Small.value),
                    onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.lamp), contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Padding.Medium.value),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Language",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("English", style = MaterialTheme.typography.headlineSmall)
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.l5_x_mark),
                        contentDescription = null
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Padding.Medium.value),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(shape = Dimensions.RoundedShape.ExtraLarge.value) {
                TextButton(modifier = Modifier.padding(Dimensions.Padding.Small.value), onClick = {
                    val intent = Intent(Intent.ACTION_SEND).run {
                        type = "plain/text"
                        putExtra(
                            Intent.EXTRA_EMAIL,
                            // TODO Support email
                            ""
                        )
                    }

                    val intentChooser = Intent.createChooser(intent, "Share via mail app")
                    context.startActivity(intentChooser)

                }) {
                    Text(text = "Support", style = MaterialTheme.typography.displaySmall)
                }
            }

            Card(shape = Dimensions.RoundedShape.ExtraLarge.value) {
                TextButton(modifier = Modifier.padding(Dimensions.Padding.Small.value), onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, "https://www.google.com".toUri())
                    context.startActivity(intent)
                }) {
                    Text(text = "Share", style = MaterialTheme.typography.displaySmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(
//                Dimensions.Padding.Small.value,
//                alignment = Alignment.CenterHorizontally
//            ),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            ShareButton(drawableRes = R.drawable.lamp) {
//
//            }
//            ShareButton(drawableRes = R.drawable.lamp) {
//
//            }
//            ShareButton(drawableRes = R.drawable.lamp) {
//
//            }
//            ShareButton(drawableRes = R.drawable.lamp) {
//
//            }
//        }

        Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

        Row {
            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://www.google.com".toUri())
                context.startActivity(intent)

            }) {
                Text(text = "Privacy Policy")
            }

            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://www.google.com".toUri())
                context.startActivity(intent)
            }) {
                Text(text = "Terms of Use")
            }
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    WordefullTheme {
        Surface {
            SettingsScreen()
        }
    }
}