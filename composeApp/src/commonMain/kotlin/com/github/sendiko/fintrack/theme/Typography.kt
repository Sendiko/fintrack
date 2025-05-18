package com.github.sendiko.fintrack.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import fintrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font


@Composable
fun PlusJakartaSansFamily() = FontFamily(
    Font(resource = Res.font.plusjakartasans_extralight, weight = FontWeight.ExtraLight),
    Font(
        resource = Res.font.plusjakartasans_extralightitalic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(resource = Res.font.plusjakartasans_light, weight = FontWeight.Light),
    Font(resource = Res.font.plusjakartasans_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resource = Res.font.plusjakartasans_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.plusjakartasans_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resource = Res.font.plusjakartasans_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.plusjakartasans_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resource = Res.font.plusjakartasans_semibold, weight = FontWeight.SemiBold),
    Font(
        resource = Res.font.plusjakartasans_semibolditalic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
    Font(resource = Res.font.plusjakartasans_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.plusjakartasans_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resource = Res.font.plusjakartasans_extrabold, weight = FontWeight.ExtraBold),
    Font(
        resource = Res.font.plusjakartasans_extrabolditalic,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Italic
    ),
)

@Composable
fun PlusJakartaSansTypography() = Typography().run {
    val fontFamily = PlusJakartaSansFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}
