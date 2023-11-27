package com.gamovation.feature.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.net.toUri
import com.gamovation.core.domain.enums.ApplicationLocales
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.core.ui.theme.boardBackgroundColor
import java.util.Locale
import kotlinx.coroutines.delay

@Composable
fun SettingsScreen(
    onUpdateAppLocale: (Locale?) -> Unit,
    onRestorePurchases: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleBar(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

            SocialBar()
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

            LanguageChooser(onUpdateAppLocale = onUpdateAppLocale)
        }

        SettingsBottomContent(onRestorePurchases = onRestorePurchases)
    }
}

@Composable
fun TitleBar(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.gamovation.feature.settings.R.string.settings),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
        Box(contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(id = R.drawable.l5_o_mark),
                contentDescription = null
            )
            IconButton(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lamp),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun SocialBar() {
    val context = LocalContext.current
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
                            Intent.EXTRA_TEXT,
                            context.getString(
                                com.gamovation.feature.settings.R.string.share_content
                            )
                        )
                    }

                    val intentChooser = Intent.createChooser(
                        intent,
                        context.getString(com.gamovation.feature.settings.R.string.share)
                    )
                    context.startActivity(intentChooser)
                }
            ) {
                Text(
                    text = stringResource(id = com.gamovation.feature.settings.R.string.share),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Card(shape = Dimensions.RoundedShape.ExtraLarge.value) {
            TextButton(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                onClick = {
                    Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:") // only email apps should handle this
                        putExtra(
                            Intent.EXTRA_EMAIL,
                            context.getString(
                                com.gamovation.feature.settings.R.string.developer_email
                            )
                        )
                        val intent = Intent.createChooser(
                            this,
                            context.getString(com.gamovation.feature.settings.R.string.contact_us)
                        )
                        context.startActivity(intent)
                    }
                }
            ) {
                Text(
                    text = stringResource(com.gamovation.feature.settings.R.string.support),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun LanguageChooser(onUpdateAppLocale: (Locale?) -> Unit) {
    var locale by remember { mutableStateOf(Locale.getDefault()) }
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(Dimensions.Padding.Medium.value)
            .clickableNoRipple { showDialog = !showDialog },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.gamovation.feature.settings.R.string.language),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))
        Text(
            text = locale.displayLanguage,
            style = MaterialTheme.typography.titleMedium
        )
    }

    LanguageDropdownMenu(
        showDialog = showDialog,
        locale = locale,
        onUpdateLocale = { newLocale ->
            newLocale?.let {
                locale = it
                onUpdateAppLocale(it)
            }
            showDialog = false
        }
    )
}

@Composable
fun LanguageDropdownMenu(showDialog: Boolean, locale: Locale, onUpdateLocale: (Locale?) -> Unit) {
    var showDropdownMenu by remember { mutableStateOf(false) }
    var bufferLocale by remember { mutableStateOf(locale) }

    AnimatedVisibility(visible = showDialog) {
        Dialog(onDismissRequest = { onUpdateLocale(null) }) {
            ChalkBoardCard(
                modifier = Modifier.padding(Dimensions.Padding.Small.value)
            ) {
                Column {
                    Text(
                        text = bufferLocale.displayLanguage,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .clickableNoRipple { showDropdownMenu = true }
                            .padding(8.dp)
                            .animateContentSize(tween(Durations.Medium.time))
                    )
                    DropdownMenu(expanded = showDropdownMenu, onDismissRequest = { }) {
                        ApplicationLocales.entries.forEach {
                            val applicationLocale = Locale(it.code)
                            DropdownMenuItem(
                                text = { Text(text = applicationLocale.displayLanguage) },
                                onClick = {
                                    bufferLocale = applicationLocale
                                    showDropdownMenu = false
                                }
                            )
                        }
                    }
                    TextButton(
                        modifier = Modifier.padding(Dimensions.Padding.Small.value),
                        onClick = {
                            onUpdateLocale(bufferLocale)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.submit),
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.SettingsBottomContent(onRestorePurchases: () -> Unit) {
    val context = LocalContext.current

    var showLoadingDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = showLoadingDialog) {
        if (showLoadingDialog) {
            delay(5000)
            showLoadingDialog = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(bottom = Dimensions.Padding.Large.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                context.getString(com.gamovation.feature.settings.R.string.privacy_policy_link)
                    .toUri()
            )
            context.startActivity(intent)
        }) {
            Text(
                text = stringResource(com.gamovation.feature.settings.R.string.privacy_policy),
                style = MaterialTheme.typography.titleMedium
            )
        }
        TextButton(onClick = {
            onRestorePurchases()
            showLoadingDialog = true
        }) {
            Text(
                text = stringResource(com.gamovation.feature.settings.R.string.restore_purchases),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxSize()
            .background(boardBackgroundColor),
        visible = showLoadingDialog
    ) {
        AlertDialog(
            modifier = Modifier.wrapContentSize(),
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Card(
                modifier = Modifier.wrapContentSize(),
                colors = CardDefaults.cardColors(boardBackgroundColor)
            ) {
                Box(
                    modifier = Modifier.padding(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp),
                        strokeWidth = 8.dp
                    )
                }
            }
        }
    }
}
