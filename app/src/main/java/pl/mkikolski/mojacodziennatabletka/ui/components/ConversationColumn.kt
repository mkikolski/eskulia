package pl.mkikolski.mojacodziennatabletka.ui.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import pl.mkikolski.mojacodziennatabletka.ui.models.Message
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive

@Composable
fun ConversationColumn(
    messages: List<Message>,
    onMessageSent: (String) -> Unit,
    topic: String,
    isBotWriting: Boolean
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.dots_loader
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    var message by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)

    ) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)
        ) {
            ChatHeader(title = topic)
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 8.dp)
            ) {
                messages.forEach { message ->
                    ChatMessage(
                        content = message.content,
                        isBot = !message.fromMe
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart)
        ) {
            if (isBotWriting) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Bot is writing",
                        fontFamily = jakartaFontFamily,
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                    Spacer(modifier = Modifier.width(2.dp))
                    LottieAnimation(
                        composition = preloaderLottieComposition,
                        progress = preloaderProgress,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    StyledTextField(
                        label = "Type a message",
                        value = message,
                        onValueChange = { message = it },
                        validator = {true},
                        errorMessage = "",
                        icon = 0,
                        placeholder = "Type a message",
                        modifier = Modifier.weight(1f),
                        paddingTop = 0.dp, paddingRight = 0.dp, paddingBottom = 0.dp, paddingLeft = 0.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    StyledIconButtonBackground(
                        icon = R.drawable.baseline_send_24,
                        size = 56.dp,
                        onClick = {
                            onMessageSent(message)
                            message = ""
                        },
                        contentColor = Color.White,
                        colorEnabled = BlueActive
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ConversationColumnPreview() {
    ConversationColumn(
        messages = listOf(
            Message("Hello", true),
            Message("Hi!", false),
            Message("How are you?", true),
            Message("I'm fine, thank you!", false),
            Message("What's up?", true),
            Message("Nothing much, just chilling", false),
            Message("Cool!", true),
            Message("Yeah", false),
            Message("Bye!", true),
            Message("Bye!", false),
        ),
        onMessageSent = {},
        topic = "Chat",
        isBotWriting = true
    )
}