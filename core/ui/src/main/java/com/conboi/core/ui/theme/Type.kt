package com.conboi.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.conboi.core.ui.R


val PangolinFont = FontFamily(
    Font(R.font.pangolin_regular)
)
val standardTypography = Typography()

val sw480PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 66.sp, lineHeight = 72.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 52.sp, lineHeight = 68.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 42.sp, lineHeight = 48.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 37.sp, lineHeight = 44.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 33.sp, lineHeight = 40.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 29.sp, lineHeight = 36.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 25.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 24.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 20.sp, lineHeight = 24.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 20.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 16.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    )
)


val sw460PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 64.sp, lineHeight = 70.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 52.sp, lineHeight = 66.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 40.sp, lineHeight = 46.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 36.sp, lineHeight = 42.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 32.sp, lineHeight = 38.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 28.sp, lineHeight = 34.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 25.sp, lineHeight = 30.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 19.sp, lineHeight = 24.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 20.sp, lineHeight = 24.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 20.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 16.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    )
)

val sw440PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 60.sp, lineHeight = 66.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 48.sp, lineHeight = 62.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 38.sp, lineHeight = 44.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 34.sp, lineHeight = 40.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 30.sp, lineHeight = 36.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 26.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 24.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 22.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 18.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 19.sp, lineHeight = 22.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 18.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 14.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 18.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    )
)


val sw412DefaultPangolinTypography =
    Typography(
        displayLarge = standardTypography.displayLarge.copy(
            fontFamily = PangolinFont,
            fontSize = 57.sp,
            lineHeight = 64.0.sp,
            letterSpacing = (-0.2).sp
        ),
        displayMedium = standardTypography.displayMedium.copy(
            fontFamily = PangolinFont,
            fontSize = 45.sp,
            lineHeight = 62.0.sp,
            letterSpacing = 0.0.sp
        ),
        displaySmall = standardTypography.displaySmall.copy(
            fontFamily = PangolinFont,
            fontSize = 36.sp,
            lineHeight = 44.0.sp,
            letterSpacing = 0.0.sp
        ),
        headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 32.sp, lineHeight = 40.0.sp, letterSpacing = 0.0.sp
    ),
    headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 28.sp, lineHeight = 36.0.sp, letterSpacing = 0.0.sp
    ),
    headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 24.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ),
    titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 22.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ),
    titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 24.0.sp, letterSpacing = 0.2.sp
    ),
    titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ),
    bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 24.0.sp, letterSpacing = 0.5.sp
    ),
    bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 20.0.sp, letterSpacing = 0.2.sp
    ),
    bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 16.0.sp, letterSpacing = 0.4.sp
    ),
    labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ),
    labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    ),
    labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    ),
)

val sw420PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 58.sp, lineHeight = 64.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 46.sp, lineHeight = 62.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 37.sp, lineHeight = 44.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 33.sp, lineHeight = 40.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 29.sp, lineHeight = 36.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 25.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 22.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 24.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 24.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 20.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 16.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 20.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    )

)

val sw400PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 52.sp, lineHeight = 58.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 43.sp, lineHeight = 54.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 33.sp, lineHeight = 40.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 31.sp, lineHeight = 36.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 28.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 25.sp, lineHeight = 30.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 22.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 22.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 18.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 22.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 18.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 14.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 18.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    )
)


val sw380PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 48.sp, lineHeight = 54.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 40.sp, lineHeight = 50.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 31.sp, lineHeight = 38.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 29.sp, lineHeight = 34.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 26.sp, lineHeight = 30.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 23.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 20.sp, lineHeight = 26.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 20.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 16.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 20.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 16.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 16.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    )
)


val sw360PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 46.sp, lineHeight = 52.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 38.sp, lineHeight = 48.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 29.sp, lineHeight = 36.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 27.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 24.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 21.sp, lineHeight = 26.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 24.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 18.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 18.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 14.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 16.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    )
)

val sw340PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 42.sp, lineHeight = 48.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 34.sp, lineHeight = 44.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 28.sp, lineHeight = 32.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 26.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 23.sp, lineHeight = 24.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 20.sp, lineHeight = 22.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 20.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 16.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 14.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 14.sp, lineHeight = 16.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 14.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 14.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 10.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    )
)


val sw320PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 40.sp, lineHeight = 44.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 32.sp, lineHeight = 40.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 26.sp, lineHeight = 30.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 24.sp, lineHeight = 26.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 22.sp, lineHeight = 22.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 18.sp, lineHeight = 20.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 16.sp, lineHeight = 18.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 12.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 13.sp, lineHeight = 14.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 12.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 10.sp, lineHeight = 12.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 14.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 10.sp, lineHeight = 12.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 9.sp, lineHeight = 12.0.sp, letterSpacing = 0.5.sp
    )
)

val sw300PangolinTypography = Typography(
    displayLarge = standardTypography.displayLarge.copy(
        fontFamily = PangolinFont, fontSize = 38.sp, lineHeight = 42.0.sp, letterSpacing = (-0.2).sp
    ), displayMedium = standardTypography.displayMedium.copy(
        fontFamily = PangolinFont, fontSize = 30.sp, lineHeight = 38.0.sp, letterSpacing = 0.0.sp
    ), displaySmall = standardTypography.displaySmall.copy(
        fontFamily = PangolinFont, fontSize = 24.sp, lineHeight = 28.0.sp, letterSpacing = 0.0.sp
    ), headlineLarge = standardTypography.headlineLarge.copy(
        fontFamily = PangolinFont, fontSize = 23.sp, lineHeight = 24.0.sp, letterSpacing = 0.0.sp
    ), headlineMedium = standardTypography.headlineMedium.copy(
        fontFamily = PangolinFont, fontSize = 20.sp, lineHeight = 20.0.sp, letterSpacing = 0.0.sp
    ), headlineSmall = standardTypography.headlineSmall.copy(
        fontFamily = PangolinFont, fontSize = 17.sp, lineHeight = 18.0.sp, letterSpacing = 0.0.sp
    ), titleLarge = standardTypography.titleLarge.copy(
        fontFamily = PangolinFont, fontSize = 15.sp, lineHeight = 16.0.sp, letterSpacing = 0.0.sp
    ), titleMedium = standardTypography.titleMedium.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 12.0.sp, letterSpacing = 0.2.sp
    ), titleSmall = standardTypography.titleSmall.copy(
        fontFamily = PangolinFont, fontSize = 10.sp, lineHeight = 10.0.sp, letterSpacing = 0.1.sp
    ), bodyLarge = standardTypography.bodyLarge.copy(
        fontFamily = PangolinFont, fontSize = 12.sp, lineHeight = 12.0.sp, letterSpacing = 0.5.sp
    ), bodyMedium = standardTypography.bodyMedium.copy(
        fontFamily = PangolinFont, fontSize = 10.sp, lineHeight = 10.0.sp, letterSpacing = 0.2.sp
    ), bodySmall = standardTypography.bodySmall.copy(
        fontFamily = PangolinFont, fontSize = 9.sp, lineHeight = 10.0.sp, letterSpacing = 0.4.sp
    ), labelLarge = standardTypography.labelLarge.copy(
        fontFamily = PangolinFont, fontSize = 11.sp, lineHeight = 12.0.sp, letterSpacing = 0.1.sp
    ), labelMedium = standardTypography.labelMedium.copy(
        fontFamily = PangolinFont, fontSize = 9.sp, lineHeight = 10.0.sp, letterSpacing = 0.5.sp
    ), labelSmall = standardTypography.labelSmall.copy(
        fontFamily = PangolinFont, fontSize = 8.sp, lineHeight = 10.0.sp, letterSpacing = 0.5.sp
    )
)