package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.AvatarPicker
import pl.mkikolski.mojacodziennatabletka.ui.components.ImageUploadBox
import pl.mkikolski.mojacodziennatabletka.ui.components.NumberPicker
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledDivider
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun AvatarSelectFormView(
    onContinue: () -> Unit = {}
) {
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(
                text = "Select your avatar",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Pick one of the avatars we've prepared for you.",
                fontSize = 20.sp,
                fontFamily = jakartaFontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            AvatarPicker(
                imageIds = avatarList,
                selectedIndex = selectedAvatar.value,
                onAvatarSelected = { selectedAvatar.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            StyledDivider(
                text = "or",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Title(
                text = "Upload your own photo",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ImageUploadBox(
                onImageSelected = { Log.d("AvatarSelectFormView", "Image selected: $it") },
                onImageDeleted = { Log.d("AvatarSelectFormView", "Image deleted") },
                imageUrl = "",
                maxSize = 5,
                allowedFormats = listOf("*.jpg", "*.png")
            )
        }

        StyledIconButtonBackground(
            icon = R.drawable.baseline_arrow_forward_24,
            onClick = { onContinue() },
            size = 96.dp,
            colorEnabled = Color.Black,
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd)

        )
    }
}

@Preview
@Composable
fun AvatarSelectFormViewPreview() {
    PillAssistantTheme {
        Surface(modifier = Modifier.background(BackgroundColor)) {
            AvatarSelectFormView()
        }
    }
}