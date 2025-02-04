package pl.mkikolski.mojacodziennatabletka.data

/**
 * Data class representing user data stored in Firestore.
 *
 * @property uid The unique identifier of the user.
 * @property email The email address of the user.
 * @property username The username of the user.
 * @property age The age of the user.
 * @property medicationIds A list of medication IDs associated with the user.
 * @property chatIds A list of chat IDs associated with the user.
 * @property avatarUrl The URL of the user's avatar.
 * @property notificationIds A list of notification IDs associated with the user.
 */
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
