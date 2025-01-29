package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Timestamp

data class ChatMessage(
    val sentByBot: Boolean = false,
    val message: String = "",
    val timestamp: Timestamp? = null
)
