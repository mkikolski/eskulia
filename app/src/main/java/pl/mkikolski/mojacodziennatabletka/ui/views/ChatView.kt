package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.data.UserViewModel
import pl.mkikolski.mojacodziennatabletka.ui.components.ChatMiniatureTile
import pl.mkikolski.mojacodziennatabletka.ui.components.CustomNavBar
import pl.mkikolski.mojacodziennatabletka.ui.components.HeaderUserCard
import pl.mkikolski.mojacodziennatabletka.ui.components.MedicationCard
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive

@Composable
fun ChatView(
    viewModel: UserViewModel,
    navController: NavHostController
) {
    val chats by viewModel.chats.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(chats) {
//        val chatId = chats.last().id
//        navController.navigate("chat_detail/$chatId")
        Log.d("ChatView", "Launched effect")
    }

    CustomNavBar(navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Row (
                modifier = Modifier
                    .padding(0.dp)
                    .clip(shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(Color.White, shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 16.dp))
                    .shadow(
                        16.dp,
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        ),
                        spotColor = Color.Transparent,
                        ambientColor = AmbientShadowLight
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                StyledIconButtonBackground(
                    icon = R.drawable.baseline_arrow_back_24,
                    size = 72.dp,
                    colorEnabled = Color(0xFFD3D3D3),
                    contentColor = Color.Black,
                    onClick = {
                        navController.navigate("chat")
                    }
                )
                Title("Chats", fontSize = 20.sp, textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                if (chats.isEmpty()) {
                    Title("No active conversations", fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                } else {
                    chats.forEach {
                        ChatMiniatureTile(
                            topic = "Bot conversation",
                            lastMessage = it.lastMessage.substring(0..minOf(30, it.lastMessage.length - 1)) + "...",
                            lastMessageDate = "Today",
                            onClick = {
                                navController.navigate("chat_detail/${it.id}")
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    StyledIconButtonBackground(
                        icon = R.drawable.baseline_add_24,
                        size = 72.dp,
                        colorEnabled = BlueActive,
                        contentColor = Color.White,
                        onClick = {
                            coroutineScope.launch {
                                viewModel.addChat(navController)
                                Log.d("ChatView", "Chat added")
                                Log.d("ChatView", chats.toString())
                            }
                        }
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ChatViewPreview() {
//    ChatView()
//}