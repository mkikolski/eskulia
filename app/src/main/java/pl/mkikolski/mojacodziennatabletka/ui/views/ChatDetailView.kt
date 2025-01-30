package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import pl.mkikolski.mojacodziennatabletka.data.UserViewModel
import pl.mkikolski.mojacodziennatabletka.ui.components.ConversationColumn
import pl.mkikolski.mojacodziennatabletka.ui.models.Message

@Composable
fun ChatDetailView(
    viewModel: UserViewModel,
    navController: NavHostController,
    chatId: String
) {
    val messages by viewModel.chatMessages.collectAsState()
    val chats by viewModel.chats.collectAsState()
    val isBotWriting by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        Log.d("ChatDetailView", chats.toString())
        viewModel.getMessages(chats.first { it.id == chatId }.chatMessagesIds)
    }

    ConversationColumn(
        messages = messages.map{ message -> Message(message.message, message.sentByBot) },
        onMessageSent = { message -> {} },
        topic = "Chat",
        isBotWriting = isBotWriting
    )
}