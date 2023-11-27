package com.gamovation.tilecl.presentation

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamovation.core.domain.billing.UserVipType
import com.gamovation.core.domain.currency.CostsInfo
import com.gamovation.core.domain.currency.calculateCosts
import com.gamovation.core.ui.state.LocalCosts
import com.gamovation.core.ui.state.LocalCurrency
import com.gamovation.core.ui.state.LocalLocale
import com.gamovation.core.ui.state.LocalVipType
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.tilecl.presentation.navigation.AppContentViewModel
import com.gamovation.tilecl.presentation.navigation.WordefullAppContent
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AppContentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                splashScreenView.view.height.toFloat()
            ).apply {
                interpolator = LinearInterpolator()
                duration = 500L
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }
        super.onCreate(savedInstanceState)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check whether the initial data is ready.
                    return if (viewModel.isViewInitialized) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content isn't ready. Suspend.
                        false
                    }
                }
            }
        )

        setContent {
            WordefullTheme {
                val currency =
                    viewModel.getUserCurrency().collectAsStateWithLifecycle(initialValue = 0)
                val userVipType =
                    viewModel.getUserVipType().collectAsStateWithLifecycle(UserVipType.BASE)
                val language = viewModel.getLanguage()
                    .collectAsStateWithLifecycle(null)

                CompositionLocalProvider(
                    LocalCurrency provides currency.value,
                    LocalVipType provides userVipType.value,
                    LocalCosts provides CostsInfo().calculateCosts(userVipType.value),
                    LocalLocale provides language.value?.let { Locale(it) }
                ) {
                    val context = LocalContext.current
                    val locale = LocalLocale.current
                    val configuration = LocalConfiguration.current
                    LaunchedEffect(locale) {
                        locale?.let {
                            Locale.setDefault(locale)
                            configuration.setLocale(locale)
                            context.createConfigurationContext(configuration)
                            context.resources.updateConfiguration(
                                configuration,
                                context.resources.displayMetrics
                            )
                            viewModel.isViewInitialized = true
                        }
                    }

                    if (viewModel.isViewInitialized) {
                        WordefullAppContent()
                    }
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
        viewModel.onResumeBilling()
    }
}
