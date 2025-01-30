package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Timestamp

data class FullChat(
    val id: String = "",
    val lastMessage: String = "",
    val lastMessageDate: Timestamp? = null,
    val chatMessagesIds: List<String> = emptyList()
)
