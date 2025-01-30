package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import pl.mkikolski.mojacodziennatabletka.data.UserViewModel
import pl.mkikolski.mojacodziennatabletka.network.MedicineApi
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
    var isBotWriting by rememberSaveable { mutableStateOf(false) }
    val api = MedicineApi(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        Log.d("ChatDetailView", chats.toString())
        viewModel.getMessages(chats.first { it.id == chatId }.chatMessagesIds)
    }

    ConversationColumn(
        messages = messages.sortedBy { it.timestamp }.map{ message -> Message(message.message, message.sentByBot) },
        onMessageSent = { message -> run{
            isBotWriting = true
            api.sendMessage(message, chatId, listener = {resp ->
                Log.d("ChatDetailViewSucc", resp)
                viewModel.getMessages(chats.first { it.id == chatId }.chatMessagesIds)
                isBotWriting = false
            }, errorListener = {error ->
                Log.e("ChatDetailViewErr", error.message.toString())
                Log.e("ChatDetailViewErr", error.cause.toString())
                isBotWriting = false
            })
        } },
        topic = "Chat",
        isBotWriting = isBotWriting,
        navController = navController
    )
}