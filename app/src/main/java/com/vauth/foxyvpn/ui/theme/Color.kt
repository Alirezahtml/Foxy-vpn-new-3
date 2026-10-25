package com.vauth.foxyvpn.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import com.vauth.foxyvpn.data.FoxyColorTheme

val FoxSeed = Color(0xFFFF5E1A)

// Soft Neumorphism Light Palette (Warm-Cool Porcelain with tactile dual shadows)
val NeuLightBg = Color(0xFFE6E9EF)
val NeuLightSurface = Color(0xFFECEFF4)
val NeuLightSurfaceElevated = Color(0xFFDDE2EB)
val NeuLightShadowLight = Color(0xFFFFFFFF)
val NeuLightShadowDark = Color(0xFFC2C9D6)

val md_theme_light_primary = Color(0xFFF25613)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFFFDBD0)
val md_theme_light_onPrimaryContainer = Color(0xFF3A0B00)
val md_theme_light_inversePrimary = Color(0xFFFFB59D)

val md_theme_light_secondary = Color(0xFF2563EB)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFDBEAFE)
val md_theme_light_onSecondaryContainer = Color(0xFF0E1D2E)

val md_theme_light_tertiary = Color(0xFF10B981)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFD1FAE5)
val md_theme_light_onTertiaryContainer = Color(0xFF042F2E)

val md_theme_light_error = Color(0xFFDC2626)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_errorContainer = Color(0xFFFEE2E2)
val md_theme_light_onErrorContainer = Color(0xFF450A0A)

val md_theme_light_background = NeuLightBg
val md_theme_light_onBackground = Color(0xFF181B22)
val md_theme_light_surface = NeuLightSurface
val md_theme_light_onSurface = Color(0xFF181B22)
val md_theme_light_surfaceVariant = Color(0xFFDCE1EA)
val md_theme_light_onSurfaceVariant = Color(0xFF525B6C)
val md_theme_light_surfaceTint = md_theme_light_primary

val md_theme_light_surfaceContainerLowest = Color(0xFFFFFFFF)
val md_theme_light_surfaceContainerLow = Color(0xFFF2F4F8)
val md_theme_light_surfaceContainer = NeuLightSurface
val md_theme_light_surfaceContainerHigh = Color(0xFFE2E6EE)
val md_theme_light_surfaceContainerHighest = Color(0xFFD8DEE8)
val md_theme_light_surfaceBright = Color(0xFFF8FAFC)
val md_theme_light_surfaceDim = Color(0xFFD2D8E4)

val md_theme_light_outline = Color(0xFF94A3B8)
val md_theme_light_outlineVariant = Color(0xFFCBD5E1)
val md_theme_light_inverseSurface = Color(0xFF1D1F24)
val md_theme_light_inverseOnSurface = Color(0xFFF1F5F9)
val md_theme_light_scrim = Color(0xFF000000)

// Soft Neumorphism Dark Palette combining Image 1 (Smart Home Matte Charcoal #1E2025 + Sunset Orange #FF5E1A)
// and Image 2 (Deep Matte Obsidian #17191E + Electric Royal Blue #2E65F3 & Emerald #18C964)
val NeuDarkBg = Color(0xFF1D1F24)
val NeuDarkSurface = Color(0xFF23262D)
val NeuDarkSurfaceElevated = Color(0xFF17191E)
val NeuDarkShadowLight = Color(0xFF2F343F)
val NeuDarkShadowDark = Color(0xFF0D0E12)

val md_theme_dark_primary = Color(0xFFFF5E1A)
val md_theme_dark_onPrimary = Color(0xFFFFFFFF)
val md_theme_dark_primaryContainer = Color(0xFF692207)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFDBD0)
val md_theme_dark_inversePrimary = Color(0xFFF25613)

val md_theme_dark_secondary = Color(0xFF2E65F3)
val md_theme_dark_onSecondary = Color(0xFFFFFFFF)
val md_theme_dark_secondaryContainer = Color(0xFF1E3A8A)
val md_theme_dark_onSecondaryContainer = Color(0xFFDBEAFE)

val md_theme_dark_tertiary = Color(0xFF18C964)
val md_theme_dark_onTertiary = Color(0xFF042F2E)
val md_theme_dark_tertiaryContainer = Color(0xFF065F46)
val md_theme_dark_onTertiaryContainer = Color(0xFFD1FAE5)

val md_theme_dark_error = Color(0xFFF43F5E)
val md_theme_dark_onError = Color(0xFFFFFFFF)
val md_theme_dark_errorContainer = Color(0xFF7F1D1D)
val md_theme_dark_onErrorContainer = Color(0xFFFEE2E2)

val md_theme_dark_background = NeuDarkBg
val md_theme_dark_onBackground = Color(0xFFF4F6FA)
val md_theme_dark_surface = NeuDarkSurface
val md_theme_dark_onSurface = Color(0xFFF4F6FA)
val md_theme_dark_surfaceVariant = Color(0xFF282C35)
val md_theme_dark_onSurfaceVariant = Color(0xFF9BA3B2)
val md_theme_dark_surfaceTint = md_theme_dark_primary

val md_theme_dark_surfaceContainerLowest = Color(0xFF121418)
val md_theme_dark_surfaceContainerLow = Color(0xFF191B20)
val md_theme_dark_surfaceContainer = NeuDarkSurface
val md_theme_dark_surfaceContainerHigh = Color(0xFF282C34)
val md_theme_dark_surfaceContainerHighest = Color(0xFF303540)
val md_theme_dark_surfaceBright = Color(0xFF383E4B)
val md_theme_dark_surfaceDim = Color(0xFF15171B)

val md_theme_dark_outline = Color(0xFF434957)
val md_theme_dark_outlineVariant = Color(0xFF2C303A)
val md_theme_dark_inverseSurface = Color(0xFFF4F6FA)
val md_theme_dark_inverseOnSurface = Color(0xFF1D1F24)
val md_theme_dark_scrim = Color(0xFF000000)

data class FoxyStatusColors(
    val connected: Color,
    val connecting: Color,
    val disconnected: Color,
)

val LightStatusColors = FoxyStatusColors(
    connected = Color(0xFF2563EB),
    connecting = Color(0xFFF25613),
    disconnected = Color(0xFF64748B),
)

val DarkStatusColors = FoxyStatusColors(
    connected = Color(0xFF2E65F3),
    connecting = Color(0xFFFF5E1A),
    disconnected = Color(0xFF8E96A6),
)

@Suppress("UNUSED_PARAMETER")
fun statusColorsForTheme(darkTheme: Boolean, colorTheme: FoxyColorTheme): FoxyStatusColors {
    return if (darkTheme) DarkStatusColors else LightStatusColors
}

val LocalFoxyStatusColors = staticCompositionLocalOf { DarkStatusColors }
