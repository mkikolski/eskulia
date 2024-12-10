package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

//TODO: Limit the size of the message bubble to 70% of the screen width
@Composable
fun ChatMessage(
    content: String,
    isBot: Boolean
) {
    Row(
        horizontalArrangement = if (isBot) androidx.compose.foundation.layout.Arrangement.Start else androidx.compose.foundation.layout.Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            shape = if (isBot) RoundedCornerShape(topStart = MaterialTheme.shapes.medium.topStart, topEnd = MaterialTheme.shapes.medium.topEnd, bottomStart = CornerSize(0.dp), bottomEnd = MaterialTheme.shapes.medium.bottomEnd) else RoundedCornerShape(bottomStart = MaterialTheme.shapes.medium.bottomStart, bottomEnd = CornerSize(0.dp), topStart = MaterialTheme.shapes.medium.topStart, topEnd = MaterialTheme.shapes.medium.topEnd),
            colors = CardDefaults.cardColors(
                containerColor = if (isBot) Color.White else BlueActive,
                contentColor = if (isBot) Color.Black else Color.White
            ),
        ) {
            Text(
                text = content,
                fontFamily = jakartaFontFamily,
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}