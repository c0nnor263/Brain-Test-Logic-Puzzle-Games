package com.conboi.wordefull.presentation

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.conboi.core.domain.billing.UserVipType
import com.conboi.core.domain.currency.CostsInfo
import com.conboi.core.domain.currency.calculateCosts
import com.conboi.core.ui.state.LocalCosts
import com.conboi.core.ui.state.LocalCurrency
import com.conboi.core.ui.state.LocalVipType
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.wordefull.presentation.navigation.AppContentViewModel
import com.conboi.wordefull.presentation.navigation.WordefullAppContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AppContentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WordefullTheme {

                val currency =
                    viewModel.getUserCurrency().collectAsStateWithLifecycle(initialValue = 0)
                val userVipType =
                    viewModel.getUserVipType().collectAsStateWithLifecycle(UserVipType.BASE)
                CompositionLocalProvider(
                    LocalCurrency provides currency.value,
                    LocalVipType provides userVipType.value,
                    LocalCosts provides CostsInfo().calculateCosts(userVipType.value),
                ) {
                    WordefullAppContent()
                }
            }


        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(WindowInsets.Type.systemBars())
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.queryProducts()
    }
}