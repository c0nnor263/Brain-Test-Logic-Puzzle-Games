package com.conboi.feature.settings

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Notifications")

        Row {
            Text("Language")
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
            Text("English")
            IconButton(onClick = { }) {

                Icon(painter = painterResource(id = R.drawable.x_mark), contentDescription = null)
            }
        }


        Row {
            TextButton(onClick = {
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
                Text(text = "Support")
            }

            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://www.google.com".toUri())
                context.startActivity(intent)
            }) {
                Text(text = "Share")
            }
        }




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


@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}