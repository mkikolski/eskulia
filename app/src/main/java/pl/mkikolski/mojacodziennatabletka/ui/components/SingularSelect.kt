package pl.mkikolski.mojacodziennatabletka.ui.components

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun SingularSelectItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    chip: String? = null,
    secondaryText: String? = null
) {
    @Composable
    fun changeCardModifier(isSelected: Boolean): Modifier {
        val cardModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable {
                onClick()
            }

        Log.d("MultiSelectItem", "isSelected: $isSelected")

        return if (isSelected) {
            cardModifier
                .border(4.dp, BlueActive.copy(alpha = 0.3f), MaterialTheme.shapes.medium)
        } else {
            cardModifier
                .shadow(30.dp, MaterialTheme.shapes.medium, spotColor = Color.Transparent, ambientColor = AmbientShadowLight)
        }
    }

    OutlinedCard (
        border = BorderStroke(if (isSelected) 4.dp else 0.dp, if (isSelected) BlueActive.copy(alpha = 0.3f) else Color.Transparent),
        shape = MaterialTheme.shapes.medium,
        colors = if (isSelected) {
            CardDefaults.outlinedCardColors(
                containerColor = BlueActive,
                contentColor = Color.White
            )
        } else {
            CardDefaults.outlinedCardColors(
                containerColor = Color.White,
                contentColor = DarkGrayInactive
            )
        },
        modifier = changeCardModifier(isSelected)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                text = text,
                color = if (isSelected) Color.White else DarkGrayInactive,
                fontFamily = jakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            if (chip != null) {
                SuggestionChip(
                    label = { Text(chip, fontFamily = jakartaFontFamily, fontSize = 14.sp, color = if (isSelected) BlueActive else Color.Black) },
                    onClick = {},
                    modifier = Modifier.padding(0.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = if (isSelected) Color.White else LightGrayInactive,
                        labelColor = if (isSelected) BlueActive else Color.Black
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (secondaryText != null) {
                Text(
                    text = secondaryText,
                    color = if (isSelected) Color.White else DarkGrayInactive,
                    fontFamily = jakartaFontFamily,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(20.dp)
                    .border(2.dp, if (isSelected) BlueActive else DarkGrayInactive, MaterialTheme.shapes.small)
                    .background(Color.White, MaterialTheme.shapes.small)
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = BlueActive,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SingularSelect(
    labels: List<String>,
    chips: List<String?>,
    secondaryTexts: List<String?>,
    selected: String,
    onSelected: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        labels.forEach { option ->
            SingularSelectItem(
                text = option,
                isSelected = option == selected,
                onClick = { onSelected(option) },
                chip = chips.getOrNull(labels.indexOf(option)),
                secondaryText = secondaryTexts.getOrNull(labels.indexOf(option))
            )
        }
    }
}