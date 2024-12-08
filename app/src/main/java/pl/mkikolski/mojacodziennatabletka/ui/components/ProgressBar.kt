package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    modifier: Modifier,
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Brush,
    percent: Int
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .clip(shape = RoundedCornerShape(12.dp))
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor, shape = RoundedCornerShape(12.dp))
                .clip(shape = RoundedCornerShape(12.dp))
                .width(width * percent / 100)
        )
    }
}

@Preview
@Composable
fun ProgressBarPreview() {
    Surface {
        ProgressBar(
            modifier = Modifier.height(8.dp),
            width = 150.dp,
            backgroundColor = Color(0xFFF3F3F3),
            foregroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF00FF00), Color(0xFF00FF00)),
                startX = 0f,
                endX = 100f
            ),
            percent = 50
        )
    }
}