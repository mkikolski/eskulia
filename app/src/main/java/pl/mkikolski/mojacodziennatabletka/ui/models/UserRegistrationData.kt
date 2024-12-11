package pl.mkikolski.mojacodziennatabletka.ui.models

data class UserRegistrationData(
    val username: String,
    val age: Int,
    val allergies: List<String>,
    val reasons: List<String>
)
