package com.conboi.wordefull.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.wordefull.presentation.navigation.WordefullAppContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WordefullTheme {
                WordefullAppContent()
            }


        }
    }
}