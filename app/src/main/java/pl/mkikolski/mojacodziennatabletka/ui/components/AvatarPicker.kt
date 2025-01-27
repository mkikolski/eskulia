package pl.mkikolski.mojacodziennatabletka.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueTintColorMatrix
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme

@Composable
fun AvatarPicker(
    imageIds: List<Int>,
    selectedIndex: Int,
    onAvatarSelected: (Int) -> Unit
) {
    var state = rememberLazyListState(selectedIndex)
    val coroutineScope = rememberCoroutineScope()

    val currentValue = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(!state.isScrollInProgress) {
        coroutineScope.launch {
            onAvatarSelected(currentValue.value)
            state.animateScrollToItem(index = state.firstVisibleItemIndex)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            state = state,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {
            items(count = Int.MAX_VALUE) {
                val index = it % imageIds.size
                if (it == state.firstVisibleItemIndex + 1) {
                    currentValue.value = imageIds[index]
                }
                Image(
                    painter = painterResource(id = imageIds[index]),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(116.dp),
                    colorFilter = if (it == state.firstVisibleItemIndex + 1) {
                        ColorFilter.colorMatrix(
                            ColorMatrix(BlueTintColorMatrix)
                        )
                    } else {
                        ColorFilter.colorMatrix(
                            ColorMatrix().apply {
                                setToSaturation(0f)
                            }
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(brush = Brush.linearGradient(
                0.0f to Color.White,
                0.4f to Color.Transparent,
                0.5f to Color.Transparent,
                0.6f to Color.Transparent,
                1.0f to Color.White,
                start = Offset(0.0f, -60.0f),
                end = Offset(1050.0f, -60.0f)
            ))
        )
    }
}

@Preview
@Composable
fun AvatarPickerPreview() {
    val avatarList = listOf(
        R.drawable.avatar_1,
        R.drawable.avatar_2,
        R.drawable.avatar_3,
        R.drawable.avatar_4,
        R.drawable.avatar_5,
        R.drawable.avatar_6,
        R.drawable.avatar_7,
        R.drawable.avatar_8,
        R.drawable.avatar_9,
        R.drawable.avatar_10
    )

    var selectedAvatar = rememberSaveable { mutableStateOf(0) }

    PillAssistantTheme {
        Surface {
            AvatarPicker(
                imageIds = avatarList,
                selectedIndex = 0,
                onAvatarSelected = { selectedAvatar.value = it }
            )
        }
    }
}
