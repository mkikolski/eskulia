package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.NumberPicker
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledTextField
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun SetUserNameView(
    username: String,
    onContinue: () -> Unit = {},
    dataModifier : (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Title(
                text = "Tell us about yourself",
                fontSize = 32.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Now, tell us how we should call you.",
                fontSize = 20.sp,
                fontFamily = jakartaFontFamily,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(48.dp))

            StyledTextField(
                value = username,
                onValueChange = { dataModifier(it) },
                label = "Username",
                placeholder = "Enter your username",
                icon = 0,
                validator = { true },
                errorMessage = "",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
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