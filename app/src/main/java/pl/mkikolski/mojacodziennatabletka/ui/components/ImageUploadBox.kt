package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.dashedBorder
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun ImageUploadBox(
    onImageSelected: (String) -> Unit,
    onImageDeleted: () -> Unit,
    imageUrl: String,
    maxSize: Int = 5,
    allowedFormats: List<String> = listOf("*.jpg", "*.png")
) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
            .dashedBorder(
                color = BlueActive,
                shape = MaterialTheme.shapes.medium
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_file_upload_24),
            contentDescription = "Upload image",
            tint = BlueActive,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Max size: $maxSize MB, allowed formats: ${allowedFormats.joinToString()}",
        fontFamily = jakartaFontFamily,
        fontSize = 14.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )
}