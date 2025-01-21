package pl.mkikolski.mojacodziennatabletka.data

data class User(
    val uid: String,
    val email: String,
    val age: Int,
    val medicationIds: List<String>,
    val chatIds: List<String>,
    val avatarUrl: String,
    val notificationIds: List<String>
)
