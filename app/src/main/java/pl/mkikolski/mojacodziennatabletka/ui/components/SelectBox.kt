package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive

@Composable
fun SelectBox(
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(20.dp)
            .border(2.dp, DarkGrayInactive, MaterialTheme.shapes.small)
            .background(Color.White, MaterialTheme.shapes.small)
            .clickable{ onClick() }
    ){
        if (selected) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = BlueActive,
                modifier = Modifier.fillMaxSize().padding(2.dp)
            )
        }
    }
}