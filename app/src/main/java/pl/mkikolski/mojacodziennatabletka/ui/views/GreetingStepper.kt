package pl.mkikolski.mojacodziennatabletka.ui.views

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.GreetingStepperPart
import pl.mkikolski.mojacodziennatabletka.ui.components.ProgressBar
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.TextNavButton
import pl.mkikolski.mojacodziennatabletka.ui.models.GreetingStep

@Composable
fun GreetingStepper() {
    val allSteps = listOf(
        GreetingStep(
            title = "Keep track of your medications",
            description = "Pill Assistant is a powerful app for managing the medications you take.",
            image = R.drawable.cover_2
        ),
        GreetingStep(
            title = "Powerful AI assistant",
            description = "Our AI assistant will help you to get more concious about your health and support your decisions with latest scientific knwoledge.",
            image = R.drawable.cover_1
        )
    )

    var step = rememberSaveable { mutableStateOf(0) }

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StyledIconButtonBackground(
                icon = R.drawable.baseline_arrow_back_24,
                size = 72.dp,
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
                width = 150.dp,
                backgroundColor = Color(0xFFF3F3F3),
                foregroundColor = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF0020EE),
                        Color(0xFF9C27B0)
                    )
                ),
                percent = (step.value + 1) * 100 / allSteps.size
            )

            Spacer(modifier = Modifier.weight(.1f))

            TextNavButton(
                text = "Skip",
                onClick = {},
                disabled = step.value >= allSteps.size - 1
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreetingStepperPart(
            title = allSteps[step.value].title,
            description = allSteps[step.value].description,
            image = allSteps[step.value].image,
            onContinue = {
                if (step.value < allSteps.size - 1) {
                    step.value += 1
                } else {
                }
            },
            isLastStep = step.value == allSteps.size - 1
        )
    }
}

@Preview
@Composable
fun GreetingStepperPreview() {
    Surface (
        modifier = Modifier.fillMaxWidth().background(Color.White)
    ) {
        GreetingStepper()
    }

}