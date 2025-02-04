package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Timestamp

/**
 * Data class representing a chat message stored in firebase.
 *
 * @property sentByBot Indicates if the message was sent by a bot.
 * @property message The content of the chat message.
 * @property timestamp The timestamp of when the message was sent.
 */
data class ChatMessage(
    val sentByBot: Boolean = false,
    val message: String = "",
    val timestamp: Timestamp? = null
)
