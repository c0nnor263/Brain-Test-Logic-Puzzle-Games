package com.gamovation.tilecl.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.theme.boardBackgroundColor
import com.gamovation.core.ui.theme.boardBorderColor
import com.gamovation.tilecl.presentation.composables.BottomBarContent
import com.gamovation.tilecl.presentation.composables.header.HeaderBar

@Composable
fun WordefullAppContent() {
    val navController = rememberNavController()

    val state = MutableTransitionState(false).apply {
        targetState = true
    }

    Scaffold(
        bottomBar = {
            BottomBarContent()
        }
    ) { innerPadding ->
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            visibleState = state,
            enter = scaleIn(tween(Durations.Medium.time)),
            exit = scaleOut(tween(Durations.Medium.time))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .displayCutoutPadding()
                    .padding(Dimensions.Padding.Medium.value)
                    .background(color = boardBackgroundColor)
                    .border(Dimensions.Padding.Small.value, color = boardBorderColor)
            ) {
                HeaderBar(navController = navController)
                WordefullNavHost(navController = navController)
            }
        }
    }
}

@Preview
@Composable
fun WordefullAppContentPreview() {
    WordefullAppContent()
}
