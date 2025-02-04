package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

/**
 * A composable function that displays a single greeting stepper part.
 *
 * @param title The title of the step.
 * @param description The description of the step.
 * @param image The resource ID of the image to display.
 * @param onContinue The callback to be invoked when the continue button is clicked.
 * @param isLastStep A boolean indicating if this is the last step.
 */
@Composable
fun GreetingStepperPart(
    title: String,
    description: String,
    image: Int,
    onContinue: () -> Unit,
    isLastStep: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Title(
                text = title,
                fontSize = 32.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                fontSize = 20.sp,
                fontFamily = jakartaFontFamily,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

        if(!isLastStep) {
            StyledIconButtonBackground(
                icon = R.drawable.baseline_arrow_forward_24,
                onClick = onContinue,
                size = 96.dp,
                colorEnabled = Color.Black,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomEnd)

            )
        } else {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                StyledButtonFullWidth(
                    "Create a free account",
                    onClick = {},
                    trailingIcon = R.drawable.baseline_arrow_forward_24,
                    leadingIcon = null,
                    enabledColor = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 12.dp, start = 32.dp, end = 32.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 48.dp, start = 32.dp, end = 32.dp)
                ){
                    Text(
                        text = "Already have an account? ",
                        fontSize = 16.sp,
                        fontFamily = jakartaFontFamily
                    )
                    Text(
                        text = "Sign in",
                        fontSize = 16.sp,
                        fontFamily = jakartaFontFamily,
                        color = Color(0xFF0020EE),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingStepperPartPreview() {
    Column(Modifier.background(color = Color.White)) {
        GreetingStepperPart(
            title = "Keep track of your medications",
            description = "Pill Assistant is a simple app that helps you to remember about your daily medications.",
            image = R.drawable.cover_1,
            onContinue = {},
            isLastStep = false
        )
    }
}