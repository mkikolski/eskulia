package pl.mkikolski.mojacodziennatabletka.data

data class Medicine(
    val name: String,
    val common_name: String,
    val pharmaceutical_form: String,
    val packaging_details: String,
    val active_substance: String
)