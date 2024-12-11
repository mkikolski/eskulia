package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (String) -> Unit,
    values: List<String>,
    modifier: Modifier = Modifier
) {
    ListPicker(
        modifier = modifier.width(120.dp),
        list = values,
        firstIndex = value  - 2,
        onSelect = {
            onValueChange(it)
        })
}

@Preview
@Composable
fun NumberPickerPreview() {
    var value by remember { mutableStateOf(5) }
    NumberPicker(
        value = value,
        onValueChange = {value = it.toInt()},
        values = (1..10).map { it.toString() }
    )
}