package pl.mkikolski.mojacodziennatabletka.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R

val jakartaFontFamily = FontFamily(
    Font(R.font.jakarta_regular),
    Font(R.font.jakarta_medium, FontWeight.W500),
    Font(R.font.jakarta_semibold, FontWeight.W600),
    Font(R.font.jakarta_bold, FontWeight.Bold),
    Font(R.font.jakarta_extralight, FontWeight.W200),
    Font(R.font.jakarta_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.jakarta_lightitalic, FontWeight.W300, FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)