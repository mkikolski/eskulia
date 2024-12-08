package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text,
        color = color,
        fontFamily = jakartaFontFamily,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        fontSize = fontSize,
        modifier = modifier
    )
}

@Preview
@Composable
fun TitlePreview() {
    Surface (
        color = BackgroundColor
    ) {
        Column {
            Title("New title")
        }
    }
}