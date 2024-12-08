package pl.mkikolski.mojacodziennatabletka.ui.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkRedError
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayDivider
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.RedError
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun StyledDivider(
    text: String,
    fontSize: TextUnit = 14.sp,
    color: Color = LightGrayDivider,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Divider(
            color = color,
            modifier = Modifier.weight(1f)
        )
        Text(
            text.uppercase(),
            fontSize = fontSize,
            fontFamily = jakartaFontFamily,
            color = color,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Divider(
            color = color,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun WarningField(
    warningTitle: String,
    warningContent: String,
    showIcon: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp)
            .border(2.dp, RedError, shape = MaterialTheme.shapes.medium)
            .background(DarkRedError, shape = MaterialTheme.shapes.medium)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Spacer(Modifier.size(4.dp))
            Icon(
                imageVector = Icons.Outlined.Warning,
                tint = RedError,
                contentDescription = null
            )
            Spacer(Modifier.size(8.dp))
            Text(
                warningTitle.uppercase() + ": ",
                fontFamily = jakartaFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(Modifier.size(4.dp))
            Text(
                warningContent,
                fontFamily = jakartaFontFamily,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun WarningBoxPreview(){
    PillAssistantTheme {
        Surface {
            WarningField(
                "Error",
                "Passwords don't match"
            )
        }
    }
}