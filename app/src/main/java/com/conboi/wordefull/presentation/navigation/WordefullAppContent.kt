package com.conboi.wordefull.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.wordefull.presentation.composables.BottomBarContent
import com.conboi.wordefull.presentation.composables.header_bar.HeaderBar
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun WordefullAppContent() {
    val navController = rememberAnimatedNavController()
    Scaffold(bottomBar = {
        BottomBarContent()
    }) { innerPadding ->
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.Medium.value)
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


@Preview
@Composable
fun WordefullAppContentPreview() {

    WordefullAppContent()
}