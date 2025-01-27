package pl.mkikolski.mojacodziennatabletka.data

data class User(
    val uid: String = "",
    val email: String = "",
    val username: String = "",
    val age: Int = 25,
    val medicationIds: List<String> = emptyList(),
    val chatIds: List<String> = emptyList(),
    val avatarUrl: String = "",
    val notificationIds: List<String> = emptyList()
)
