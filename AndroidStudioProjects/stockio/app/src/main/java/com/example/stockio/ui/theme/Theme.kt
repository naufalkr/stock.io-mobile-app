// Theme.kt - Replace your existing theme setup
package com.example.stockio.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat



val Green40 = Color(0xFF4CAF50)        // Hijau
val Red40 = Color(0xFFF44336)          // Merah
val PrimaryBlue = Color(0xFF2196F3)    // Biru utama
val SecondaryBlue = Color(0xFF64B5F6)  // Biru sekunder
// Tambahan warna custom
val Blue = Color(0xFF2196F3)
val BlueDark = Color(0xFF1565C0)
val BlueLight = Color(0xFF64B5F6)
val ModerateColor = Color(0xFF7986CB)
val ImportantColor = Color(0xFFD32F2F)
val Gray800 = Color(0xFF424242)
val Gray600 = Color(0xFF757575)


private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    primaryContainer = BlueDark,
    onPrimaryContainer = Color.White,
    secondary = BlueLight,
    onSecondary = Gray800,
    tertiary = ModerateColor,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2D2D2D),
    onSurfaceVariant = Color.LightGray,
    error = ImportantColor
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    primaryContainer = BlueLight,
    onPrimaryContainer = BlueDark,
    secondary = BlueDark,
    onSecondary = Color.White,
    tertiary = ModerateColor,
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    onSurface = Gray800,
    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Gray600,
    error = ImportantColor
)

@Composable
fun stockioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
