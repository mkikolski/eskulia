package pl.mkikolski.mojacodziennatabletka.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.models.Reason
import pl.mkikolski.mojacodziennatabletka.ui.models.TextIconContainer
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun MultiSelectItem(
    text: String,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
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
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = if (isSelected) Color.White else DarkGrayInactive,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = text,
                color = if (isSelected) Color.White else DarkGrayInactive,
                fontFamily = jakartaFontFamily,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
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
fun MultipleSelect(
    items: List<TextIconContainer>,
    selectedItems: List<TextIconContainer>,
    onItemClicked: (TextIconContainer) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items.forEach { item ->
            MultiSelectItem(
                text = item.text,
                icon = item.icon,
                isSelected = selectedItems.contains(item),
                onClick = {
                    onItemClicked(item)
                }
            )
        }
    }
}

@Preview
@Composable
fun MultiSelectItemPreview() {
    var isSelected = rememberSaveable { mutableStateOf(false) }
    PillAssistantTheme {
        Surface (
            modifier = Modifier.background(BackgroundColor)
        ) {
            MultiSelectItem(
                text = "Morning",
                icon = R.drawable.baseline_fingerprint_24,
                isSelected = isSelected.value,
                onClick = { isSelected.value = !isSelected.value }
            )
        }
    }
}

@Preview
@Composable
fun MultipleSelectPreview() {
    val items = listOf(
        Reason("Morning", R.drawable.baseline_fingerprint_24),
        Reason("Afternoon", R.drawable.baseline_fingerprint_24),
        Reason("Evening", R.drawable.baseline_fingerprint_24),
        Reason("Night", R.drawable.baseline_fingerprint_24)
    )
    val selectedItems = remember { mutableStateOf(listOf<TextIconContainer>()) }
    PillAssistantTheme {
        Surface (
            modifier = Modifier.background(BackgroundColor)
        ) {
            MultipleSelect(
                items = items,
                selectedItems = selectedItems.value,
                onItemClicked = {
                    if (selectedItems.value.contains(it)) {
                        selectedItems.value -= it
                    } else {
                        selectedItems.value += it
                    }
                }
            )
        }
    }
}
