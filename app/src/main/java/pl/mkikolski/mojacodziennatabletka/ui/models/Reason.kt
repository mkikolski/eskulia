package pl.mkikolski.mojacodziennatabletka.ui.models

data class Reason(
    override val text: String,
    override val icon: Int
) : TextIconContainer
