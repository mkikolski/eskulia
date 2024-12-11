package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun UpcomingDose() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        CalendarCard("12:00", "12", "Dec", modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp).background(Color.Transparent).weight(1f)
        ) {
            Column {
                Title(
                    text = "Medicine 1",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Active substance, 30 mg",
                    color = DarkGrayInactive,
                    fontFamily = jakartaFontFamily,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(Modifier.height(8.dp))
                Row {
                    Text(
                        text = "Quantity:",
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "1 pill",
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily,
                        fontSize = 14.sp
                    )
                }

                Row {
                    Text(
                        text = "Notes:",
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Take with food",
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarCard(
    hour: String,
    day: String,
    month: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = BlueActive,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row{
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_notifications_24),
                    contentDescription = "Notification icon",
                    tint = BackgroundColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = hour,
                    color = BackgroundColor,
                    fontFamily = jakartaFontFamily,
                    fontSize = 18.sp
                )
            }
            Spacer(Modifier.height(4.dp))
            Title(
                text = day,
                fontSize = 34.sp,
                color = Color.White
            )
            Spacer(Modifier.height(4.dp))
            Title(
                text = month,
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Today",
                color = BackgroundColor,
                fontFamily = jakartaFontFamily,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun CalendarCardPreview() {
    PillAssistantTheme {
        Surface {
            CalendarCard("12:00", "12", "Dec")
        }
    }
}

@Preview
@Composable
fun UpcomingDosePreview() {
    PillAssistantTheme {
        Surface {
            UpcomingDose()
        }
    }
}