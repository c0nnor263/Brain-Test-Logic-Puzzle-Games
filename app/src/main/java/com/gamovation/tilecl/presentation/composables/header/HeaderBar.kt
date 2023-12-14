package com.gamovation.tilecl.presentation.composables.header

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gamovation.core.navigation.Screens
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.defaultSpringAnimation
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.navigate
import com.gamovation.core.ui.popBack
import com.gamovation.core.ui.state.LocalCurrency
import com.gamovation.tilecl.presentation.composables.header.options.HeaderBarHomeOption
import com.gamovation.tilecl.presentation.composables.header.options.HeaderBarLevelOption
import com.gamovation.tilecl.presentation.composables.header.options.HeaderBarMenuOption
import com.gamovation.tilecl.presentation.composables.header.options.HeaderBarSettingsOption
import com.gamovation.tilecl.presentation.composables.header.options.HeaderBarStoreOption

@Composable
fun HeaderBar(navController: NavHostController) {
    val currency = LocalCurrency.current
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Row(
        modifier = Modifier.padding(
            vertical = Dimensions.Padding.Small.value,
            horizontal = Dimensions.Padding.Medium.value
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = currentDestination?.route ?: "",
            label = "",
            transitionSpec = {
                (
                        scaleIn(animationSpec = defaultSpringAnimation()) + fadeIn() togetherWith
                                scaleOut(animationSpec = defaultSpringAnimation()) + fadeOut()
                        )
                    .using(
                        SizeTransform(clip = false)
                    )
            }
        ) {
            Row {
                when (it) {
                    Screens.Home.route -> {
                        HeaderBarHomeOption(
                            onNavigateToSettings = {
                                navController.navigate(Screens.Settings)
                            }
                        ) {
                            navController.navigate(Screens.Menu)
                        }
                    }

                    Screens.Level().route -> {
                        HeaderBarLevelOption(
                            onNavigateToHome = {
                                navController.navigate(Screens.Home)
                            },
                            onNavigateToMenu = {
                                navController.navigate(Screens.Menu)
                            }
                        )
                    }

                    Screens.Menu.route -> {
                        HeaderBarMenuOption {
                            navController.popBack()
                        }
                    }

                    Screens.Settings.route -> {
                        HeaderBarSettingsOption {
                            navController.popBack()
                        }
                    }

                    Screens.Store.route -> {
                        HeaderBarStoreOption(
                            onNavigateBack = {
                                navController.popBack()
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1F))

        ScalableButton(
            modifier = Modifier.semantics { contentDescription = "StoreScreenNavigate" },
            onClick = {
                navController.navigate(Screens.Store)
            }
        ) {
            Card(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                shape = Dimensions.RoundedShape.Large.value
            ) {
                Row(
                    modifier = Modifier.padding(Dimensions.Padding.Small.value),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currency.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Image(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.lamp),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
