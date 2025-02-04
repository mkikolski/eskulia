package pl.mkikolski.mojacodziennatabletka.data

/**
 * Data class representing a medicine used for API interaction.
 *
 * @property name The name of the medicine.
 * @property common_name The common name of the medicine.
 * @property pharmaceutical_form The pharmaceutical form of the medicine.
 * @property packaging_details The packaging details of the medicine.
 * @property active_substance The active substance in the medicine.
 */
data class Medicine(
    val name: String,
    val common_name: String,
    val pharmaceutical_form: String,
    val packaging_details: String,
    val active_substance: String
)