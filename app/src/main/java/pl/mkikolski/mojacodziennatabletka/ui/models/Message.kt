package pl.mkikolski.mojacodziennatabletka.ui.models

import java.util.Date

data class Message(
    val content: String,
    val fromMe: Boolean,
    val time: Date = Date()
)
