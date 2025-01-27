package pl.mkikolski.mojacodziennatabletka.data

data class Medication(
    val id: String,
    val name: String,
    val remainingDoses: Int,
    val totalDoses: Int,
    val expiryDate: String
)
