package com.conboi.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.conboi.core.ui.R

val PangolinFont = FontFamily(
    Font(R.font.pangolin_regular)
)
val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = PangolinFont),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = PangolinFont),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = PangolinFont),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = PangolinFont),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = PangolinFont),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = PangolinFont),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = PangolinFont),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = PangolinFont),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = PangolinFont),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = PangolinFont),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = PangolinFont),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = PangolinFont),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = PangolinFont),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = PangolinFont),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = PangolinFont),
)