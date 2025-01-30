package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily
import java.util.Date

//TODO: Refactor to use firebase timestamp format
@Composable
fun ChatMiniatureTile(
    topic: String,
    lastMessage: String,
    lastMessageDate: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .shadow(
                30.dp,
                MaterialTheme.shapes.medium,
                spotColor = Color.Transparent,
                ambientColor = AmbientShadowLight
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Title(
            text = topic,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = lastMessage.substring(0..minOf(30, lastMessage.length - 1)),
                fontSize = 16.sp,
                fontFamily = jakartaFontFamily
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = lastMessageDate,
                fontSize = 16.sp,
                fontFamily = jakartaFontFamily
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}