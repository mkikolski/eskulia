package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import kotlinx.coroutines.launch
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.MarkdownScrollableColumn
import pl.mkikolski.mojacodziennatabletka.ui.components.SelectBox
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledButtonFullWidth
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PrivacyPolicyFormView(
    onContinue: () -> Unit = {}
) {
    var tosAccepted = rememberSaveable { mutableStateOf(false) }
    var privacyAccepted = rememberSaveable { mutableStateOf(false) }
    var sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var coroutineScope = rememberCoroutineScope()
    var selectedDocument = rememberSaveable { mutableStateOf("tos") }

    ModalBottomSheetLayout(
        // Bottom sheet state
        sheetState = sheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            ) {
                MarkdownScrollableColumn(
                    rawResourceId = when (selectedDocument.value) {
                        "tos" -> R.raw.tos
                        "privacy" -> R.raw.privacy
                        else -> R.raw.tos
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                StyledButtonFullWidth(
                    text = "Close",
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    },
                    modifier = Modifier.padding(top = 32.dp, bottom = 16.dp).align(Alignment.BottomCenter),
                    leadingIcon = null,
                    trailingIcon = R.drawable.baseline_arrow_back_24,
                )
            }
        },
        sheetShape = MaterialTheme.shapes.medium,
        sheetBackgroundColor = BackgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cover_3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(18.dp))
                Title(
                    text = "Agree to our Terms of Service",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This app provides and stores information crucial for health and well-being. Kindly check the files below. Both agreements are required to proceed.",
                    fontSize = 18.sp,
                    fontFamily = jakartaFontFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    SelectBox(
                        selected = tosAccepted.value,
                        onClick = { tosAccepted.value = !tosAccepted.value }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "I hereby agree to",
                        fontSize = 18.sp,
                        fontFamily = jakartaFontFamily,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Terms of Service",
                        fontSize = 18.sp,
                        fontFamily = jakartaFontFamily,
                        color = BlueActive,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    selectedDocument.value = "tos"
                                    sheetState.show()
                                }
                            }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    SelectBox(
                        selected = privacyAccepted.value,
                        onClick = { privacyAccepted.value = !privacyAccepted.value }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "I hereby agree to",
                        fontSize = 18.sp,
                        fontFamily = jakartaFontFamily,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Privacy Policy",
                        fontSize = 18.sp,
                        fontFamily = jakartaFontFamily,
                        color = BlueActive,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    selectedDocument.value = "privacy"
                                    sheetState.show()
                                }
                            }
                    )
                }
            }

            StyledIconButtonBackground(
                icon = R.drawable.baseline_arrow_forward_24,
                onClick = { onContinue() },
                size = 96.dp,
                colorEnabled = Color.Black,
                colorDisabled = LightGrayInactive,
                disabledContentColor = Color.Black,
                disabled = !tosAccepted.value || !privacyAccepted.value,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomEnd)

            )
        }
    }
}

@Preview
@Composable
fun PrivacyPolicyFormViewPreview() {
    PillAssistantTheme {
        Surface {
            PrivacyPolicyFormView()
        }
    }
}