package com.gamovation.feature.settings

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun SettingsScreen(onRestorePurchases: () -> Unit) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Settings", style = MaterialTheme.typography.displayMedium)
                Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier.matchParentSize(),
                        painter = painterResource(id = R.drawable.l5_o_mark),
                        contentDescription = null
                    )
                    IconButton(modifier = Modifier.padding(Dimensions.Padding.Small.value),
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.lamp),
                            contentDescription = null
                        )
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

                Card(shape = Dimensions.RoundedShape.ExtraLarge.value) {
                    TextButton(
                        modifier = Modifier.padding(Dimensions.Padding.Small.value),
                        onClick = {
                            val intent = Intent(Intent.ACTION_SEND).run {
                                type = "plain/text"
                                putExtra(
                                    Intent.EXTRA_TEXT, "Let's find out how strong you are!\n" +
                                            "\n" +
                                            "https://play.google.com/store/apps/details?id=com.gamovation.tilecl"
                                )
                            }

                            val intentChooser = Intent.createChooser(intent, "Share")
                            context.startActivity(intentChooser)

                        }) {
                        Text(text = "Share", style = MaterialTheme.typography.displaySmall)

                    }
                }

                Card(shape = Dimensions.RoundedShape.ExtraLarge.value) {
                    TextButton(
                        modifier = Modifier.padding(Dimensions.Padding.Small.value),
                        onClick = {
                            Intent(Intent.ACTION_SEND).apply {
                                type = "plain/text"
                                putExtra(
                                    Intent.EXTRA_EMAIL,
                                    "abrigojulianne08@gmail.com"
                                )
                                val intent = Intent.createChooser(this, "Contact us")
                                context.startActivity(intent)
                            }

                        }) {
                        Text(text = "Support", style = MaterialTheme.typography.displaySmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))


        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimensions.Padding.Large.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                TextButton(onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://github.com/WADevelopment/Wordefull/blob/master/privacy-policy.md".toUri()
                    )
                    context.startActivity(intent)

                }) {
                    Text(text = "Privacy Policy", style = MaterialTheme.typography.headlineSmall)
                }

                TextButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, "https://www.google.com".toUri())
                    context.startActivity(intent)
                }) {
                    Text(text = "Terms of Use", style = MaterialTheme.typography.headlineSmall)
                }
            }

            TextButton(onClick = {
                onRestorePurchases()
            }) {
                Text(text = "Restore Purchases", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    WordefullTheme {
        Surface {
            SettingsScreen {

            }
        }
    }
}