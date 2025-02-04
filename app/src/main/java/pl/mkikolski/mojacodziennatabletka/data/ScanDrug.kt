package pl.mkikolski.mojacodziennatabletka.data

/**
 * Data class representing the result of a drug scan.
 *
 * @property found Indicates if the drug was found.
 * @property drug The scanned drug details, if found.
 */
data class ScanData(
    val found: Boolean,
    val drug: ScanDrug? = null
)

/**
 * Data class representing a scanned drug.
 *
 * @property name The name of the drug.
 * @property common_name The common name of the drug.
 * @property power The power or strength of the drug.
 * @property form The pharmaceutical form of the drug.
 */
data class ScanDrug(
    val name: String,
    val common_name: String,
    val power: String,
    val form: String
)