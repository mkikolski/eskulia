package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.ProgressBar
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.TextNavButton
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme

//TODO: Refactor views to store state inside this view
@Composable
fun RegisterCompletionView() {
    var step = rememberSaveable { mutableStateOf(0) }
    val allSteps = 5

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StyledIconButtonBackground(
                icon = R.drawable.baseline_arrow_back_24,
                size = 64.dp,
                colorEnabled = Color(0xFFD3D3D3),
                contentColor = Color.Black,
                onClick = {
                    if (step.value > 0) {
                        step.value -= 1
                    } else {
                        // TODO: navigate to previous screen
                    }
                }
            )

            Spacer(modifier = Modifier.weight(.1f))

            ProgressBar(
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp),
                width = 300.dp,
                backgroundColor = Color(0xFFF3F3F3),
                foregroundColor = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF0020EE),
                        Color(0xFF9C27B0)
                    )
                ),
                percent = (step.value + 1) * 100 / allSteps
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedContent(targetState = step) { targetState ->
            when (targetState.value) {
                0 -> {
                    AgeFormView({
                        step.value += 1
                    })
                }

                1 -> {
                    ReasonsFormView({
                        step.value += 1
                    })
                }

                2 -> {
                    AvatarSelectFormView({
                        step.value += 1
                    })
                }

                3 -> {
                    PrivacyPolicyFormView({
                        step.value += 1
                    })
                }

                4 -> {
                    ProfileCompletedView()
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterCompletionViewPreview() {
    PillAssistantTheme {
        Surface {
            RegisterCompletionView()
        }
    }
}