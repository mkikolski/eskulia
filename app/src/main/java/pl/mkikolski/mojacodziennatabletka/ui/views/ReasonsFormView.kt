package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.MultipleSelect
import pl.mkikolski.mojacodziennatabletka.ui.components.NumberPicker
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.models.Reason
import pl.mkikolski.mojacodziennatabletka.ui.models.TextIconContainer
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun ReasonsFormView(
    onContinue: () -> Unit = {}
) {
    val reasons = listOf(
        Reason("Keep track of my medications", R.drawable.baseline_edit_calendar_24),
        Reason("Get and set reminders", R.drawable.baseline_notifications_24),
        Reason("Learn more about my medications", R.drawable.baseline_medication_24),
        Reason("Access AI chatbot", R.drawable.baseline_chat_24),
        Reason("Find cheaper replacements", R.drawable.baseline_attach_money_24),
        Reason("Other reason", R.drawable.baseline_edit_note_24)
    )

    var selectedReasons by remember { mutableStateOf(listOf<TextIconContainer>()) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Title(
                text = "I want to...",
                fontSize = 32.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Next, tell us why you want to use the app. Select at least one reason.",
                fontSize = 20.sp,
                fontFamily = jakartaFontFamily,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(48.dp))

            MultipleSelect(
                items = reasons,
                selectedItems = selectedReasons,
                onItemClicked = { item ->
                    selectedReasons = if (selectedReasons.contains(item)) {
                        selectedReasons.filter { it != item }
                    } else {
                        selectedReasons + item
                    }
                }
            )
        }

        StyledIconButtonBackground(
            icon = R.drawable.baseline_arrow_forward_24,
            onClick = { onContinue() },
            size = 96.dp,
            colorEnabled = Color.Black,
            colorDisabled = LightGrayInactive,
            disabledContentColor = Color.Black,
            disabled = selectedReasons.isEmpty(),
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd)

        )
    }
}

@Preview
@Composable
fun ReasonsFormViewPreview() {
    PillAssistantTheme {
        Surface {
            ReasonsFormView()
        }
    }
}