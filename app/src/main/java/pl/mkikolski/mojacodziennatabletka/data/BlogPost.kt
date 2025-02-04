package pl.mkikolski.mojacodziennatabletka.data

/**
 * Data class representing a blog post from firebase.
 *
 * @property id The unique identifier of the blog post.
 * @property title The title of the blog post.
 * @property content The content of the blog post.
 * @property author The author of the blog post.
 * @property date The publication date of the blog post.
 * @property imageUrl The URL of the image associated with the blog post.
 * @property tags A list of tags associated with the blog post.
 */
data class BlogPost(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList()
)
