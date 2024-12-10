package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mkikolski.mojacodziennatabletka.ui.components.ChatMiniatureTile
import pl.mkikolski.mojacodziennatabletka.ui.components.CustomNavBar
import pl.mkikolski.mojacodziennatabletka.ui.components.HeaderUserCard

@Composable
fun ChatView() {
    CustomNavBar() {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            HeaderUserCard("Chats")
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                ChatMiniatureTile("Topic 1", "Last message sent by bot", "22:58")
                Spacer(modifier = Modifier.height(6.dp))
                ChatMiniatureTile("Topic 2", "Last message sent by bot 2", "17:42")
                Spacer(modifier = Modifier.height(6.dp))
                ChatMiniatureTile("Topic 3", "Last message sent by bot 3", "12:31")
                Spacer(modifier = Modifier.height(6.dp))
                ChatMiniatureTile("Topic 4", "Last message sent by bot 4", "09:11")
                Spacer(modifier = Modifier.height(6.dp))
                ChatMiniatureTile("Topic 5", "Last message sent by bot 5", "08:08")
            }
        }
    }
}

@Preview
@Composable
fun ChatViewPreview() {
    ChatView()
}