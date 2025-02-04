package pl.mkikolski.mojacodziennatabletka.ui.models

/**
 * Interface representing a container for any data that holds text and an icon.
 */
interface TextIconContainer {
    /**
     * The text associated with the container.
     */
    val text: String

    /**
     * The resource ID of the icon associated with the container.
     */
    val icon: Int
}