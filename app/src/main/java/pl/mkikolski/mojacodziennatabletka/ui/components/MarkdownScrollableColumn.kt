package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.models.MarkdownText
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun MarkdownScrollableColumn(
    rawResourceId: Int
) {
    val markdownText = LocalContext.current.resources.openRawResource(rawResourceId).bufferedReader().use { it.readText() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            MarkdownText.markdownToAnnotatedString(markdownText),
            fontFamily = jakartaFontFamily
        )
    }
}

@Preview
@Composable
fun MarkdownScrollableColumnPreview() {
    PillAssistantTheme {
        Surface {
            MarkdownScrollableColumn(rawResourceId = R.raw.tos)
        }
    }
}