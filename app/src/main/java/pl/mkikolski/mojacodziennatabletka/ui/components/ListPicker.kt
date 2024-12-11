package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListPicker(
    modifier: Modifier = Modifier,
    list: List<String>,
    firstIndex: Int,
    onSelect: (String) -> Unit
) {


    val listState = rememberLazyListState(firstIndex)
    val coroutineScope = rememberCoroutineScope()

    val currentValue = remember {
        mutableStateOf("")
    }


    LaunchedEffect(!listState.isScrollInProgress) {
        coroutineScope.launch {
            onSelect(currentValue.value)
            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
        }
    }


    Box(
        modifier = Modifier
            .height(270.dp)
            .fillMaxWidth()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(count = Int.MAX_VALUE, itemContent = {
                val index = it % list.size
                if (it == listState.firstVisibleItemIndex + 1) {
                    currentValue.value = list[index]
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    modifier = modifier
                        .background(if (it == listState.firstVisibleItemIndex + 1) BlueActive else Color.Transparent, MaterialTheme.shapes.medium)
                        .alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                    text = list[index].uppercase(),
                    color = if (it == listState.firstVisibleItemIndex + 1) Color.White else Color.Black,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = jakartaFontFamily,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.02.em,
                        fontSize = 64.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
            })
        }

        Spacer(
            modifier = modifier
                .background(brush = Brush.linearGradient(
                    0.0f to BackgroundColor,
                    0.3f to Color.Transparent,
                    0.5f to Color.Transparent,
                    0.7f to Color.Transparent,
                    1.0f to BackgroundColor,
                    start = Offset(0.0f, 0.0f),
                    end = Offset(0.0f, 800.0f)
                ))
                .height(270.dp)
                .width(120.dp)
        )
    }

}