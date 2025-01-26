package pl.mkikolski.mojacodziennatabletka.data


data class ScanData(
    val found: Boolean,
    val drug: ScanDrug? = null
)

data class ScanDrug(
    val name: String,
    val common_name: String,
    val power: String,
    val form: String
)