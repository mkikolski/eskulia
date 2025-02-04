package pl.mkikolski.mojacodziennatabletka.ui.models

import android.content.res.Resources
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily
import pl.mkikolski.mojacodziennatabletka.R

/**
 * Object that provides functionality to convert Markdown text to an AnnotatedString.
 */
object MarkdownText {
    /**
     * Converts a Markdown string to an AnnotatedString with appropriate styles.
     *
     * @param markdown The Markdown string to be converted.
     * @return The resulting AnnotatedString with styles applied.
     */
    fun markdownToAnnotatedString(markdown: String): AnnotatedString {
        val builder = AnnotatedString.Builder()

        // Define regex patterns for Markdown elements
        val headerPattern = "^(#{1,6})\\s+(.*)".toRegex()
        val boldPattern = "\\*\\*(.*?)\\*\\*".toRegex()
        val italicPattern = "\\*(.*?)\\*".toRegex()
        val linkPattern = "\\[(.*?)]\\((.*?)\\)".toRegex()
        val dividerPattern = "^(\\*{3,}|-{3,}|_{3,})\\s*$".toRegex()

        // Process the Markdown string incrementally
        var currentIndex = 0
        val combinedPattern = "(#{1,6}\\s.*|\\*\\*.*?\\*\\*|\\*.*?\\*|\\[.*?\\]\\(.*?\\)|\\*{3,}|-{3,}|_{3,})".toRegex()

        combinedPattern.findAll(markdown).forEach { matchResult ->
            val range = matchResult.range
            // Append the text before the match as plain text
            if (currentIndex < range.first) {
                builder.append(markdown.substring(currentIndex, range.first))
            }

            val matchedText = matchResult.value

            when {
                headerPattern.matches(matchedText) -> {
                    val groups = headerPattern.find(matchedText)?.groupValues
                    val headerLevel = groups?.get(1)?.length ?: 1
                    val content = groups?.get(2).orEmpty()

                    val headerStyle = when (headerLevel) {
                        1 -> SpanStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        2 -> SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        3 -> SpanStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                        4 -> SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        5 -> SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal)
                        else -> SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    }

                    builder.withStyle(headerStyle) {
                        append(content)
                    }
                }
                boldPattern.matches(matchedText) -> {
                    val content = boldPattern.find(matchedText)?.groupValues?.get(1).orEmpty()
                    builder.withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(content)
                    }
                }
                italicPattern.matches(matchedText) -> {
                    val content = italicPattern.find(matchedText)?.groupValues?.get(1).orEmpty()
                    builder.withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(content)
                    }
                }
                linkPattern.matches(matchedText) -> {
                    val groups = linkPattern.find(matchedText)?.groupValues
                    val displayText = groups?.get(1).orEmpty()
                    val url = groups?.get(2).orEmpty()

                    builder.withStyle(
                        SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
                    ) {
                        pushStringAnnotation(tag = "URL", annotation = url)
                        append(displayText)
                        pop()
                    }
                }
                dividerPattern.matches(matchedText) -> {
                    // Add a divider representation (e.g., a line of dashes)
                    builder.withStyle(SpanStyle(color = Color.Gray)) {
                        append("\n──────────\n") // Visual representation of the divider
                    }
                }
            }

            // Update the current index
            currentIndex = range.last + 1
        }

        // Append any remaining plain text after the last match
        if (currentIndex < markdown.length) {
            builder.append(markdown.substring(currentIndex))
        }

        return builder.toAnnotatedString()
    }
}