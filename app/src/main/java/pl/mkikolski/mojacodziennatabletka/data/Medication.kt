package pl.mkikolski.mojacodziennatabletka.data

data class Medication(
    val id: String = "",
    val name: String = "",
    val activeSubstance: String = "",
    val dose: String = "",
    val remainingDoses: Int = 0,
    val totalDoses: Int = 0,
    val expiryDate: String = ""
)
