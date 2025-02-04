package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Timestamp

/**
 * Data class representing a full chat.
 *
 * @property id The unique identifier of the chat.
 * @property lastMessage The content of the last message in the chat.
 * @property lastMessageDate The timestamp of the last message in the chat.
 * @property chatMessagesIds A list of IDs of the messages in the chat.
 */
data class FullChat(
    val id: String = "",
    val lastMessage: String = "",
    val lastMessageDate: Timestamp? = null,
    val chatMessagesIds: List<String> = emptyList()
)
