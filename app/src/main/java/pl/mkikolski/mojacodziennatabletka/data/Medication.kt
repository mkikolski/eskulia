package pl.mkikolski.mojacodziennatabletka.data

/**
 * Data class representing a medication.
 *
 * @property id The unique identifier of the medication.
 * @property name The name of the medication.
 * @property activeSubstance The active substance in the medication.
 * @property dose The dose of the medication.
 * @property remainingDoses The number of remaining doses of the medication.
 * @property totalDoses The total number of doses of the medication.
 * @property expiryDate The expiry date of the medication.
 */
data class Medication(
    val id: String = "",
    val name: String = "",
    val activeSubstance: String = "",
    val dose: String = "",
    val remainingDoses: Int = 0,
    val totalDoses: Int = 0,
    val expiryDate: String = ""
)
