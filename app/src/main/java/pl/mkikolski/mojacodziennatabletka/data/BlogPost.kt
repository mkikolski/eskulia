package pl.mkikolski.mojacodziennatabletka.data

data class BlogPost(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList()
)
