package com.gamovation.tilecl.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.Durations
import com.gamovation.core.ui.R
import com.gamovation.tilecl.presentation.composables.BottomBarContent
import com.gamovation.tilecl.presentation.composables.header_bar.HeaderBar

@OptIn(ExperimentalAnimationApi::class)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.main_background),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )

            AnimatedVisibility(
                visibleState = state,
                enter = scaleIn(tween(Durations.Long.time)),
                exit = scaleOut(tween(Durations.Long.time))
            ) {
                Box(
                    modifier = Modifier
                        .displayCutoutPadding()
                        .padding(Dimensions.Padding.Medium.value),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.board),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(Dimensions.Padding.Medium.value)
                    ) {
                        HeaderBar(navController = navController)
                        WordefullNavHost(navController = navController)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun WordefullAppContentPreview() {
    WordefullAppContent()
}