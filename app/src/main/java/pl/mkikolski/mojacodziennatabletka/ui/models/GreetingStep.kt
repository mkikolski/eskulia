package pl.mkikolski.mojacodziennatabletka.ui.models

/**
 * Data class representing data stored in a single greeting step.
 *
 * @property title The title of the step.
 * @property description The description of the step.
 * @property image The resource ID of the image associated with the step.
 */
data class GreetingStep(
    val title: String,
    val description: String,
    val image: Int
)
