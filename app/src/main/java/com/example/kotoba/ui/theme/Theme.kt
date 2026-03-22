package com.example.kotoba.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = RedJapanese,
    secondary = BlackJapanese,
    tertiary = GrayDark,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = WhitePure,
    onSecondary = WhitePure,
    onTertiary = WhitePure,
    onBackground = WhitePure,
    onSurface = WhitePure,
)

private val LightColorScheme = lightColorScheme(
    primary = RedJapanese,
    secondary = BlackJapanese,
    tertiary = GrayLight,
    background = BeigeJapanese,
    surface = WhitePure,
    onPrimary = WhitePure,
    onSecondary = WhitePure,
    onTertiary = BlackJapanese,
    onBackground = BlackJapanese,
    onSurface = BlackJapanese,
)

@Composable
fun KotobaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Note: statusBarColor is deprecated on API 35+ but we manage visibility via insets controller
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
