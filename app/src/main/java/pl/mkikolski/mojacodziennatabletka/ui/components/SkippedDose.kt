package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkRedError
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightRedError
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun SkippedDose() {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_warning_amber_24),
                contentDescription = "Warning",
                tint = DarkRedError,
                modifier = Modifier.padding(8.dp).size(48.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Title(
                    text = "Medication 1",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.size(4.dp))
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_access_time_24),
                        contentDescription = "Time",
                        tint = DarkGrayInactive,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.size(2.dp))
                    Text(
                        text = "12:00",
                        fontSize = 16.sp,
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily
                    )
                    Spacer(Modifier.size(6.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_calendar_month_24),
                        contentDescription = "Time",
                        tint = DarkGrayInactive,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.size(2.dp))
                    Text(
                        text = "12.12.2024",
                        fontSize = 16.sp,
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SkippedDosePreview() {
    PillAssistantTheme {
        Surface(Modifier.background(BackgroundColor)) {
            SkippedDose()
        }
    }
}