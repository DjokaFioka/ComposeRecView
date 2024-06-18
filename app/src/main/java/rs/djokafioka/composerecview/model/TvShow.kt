package rs.djokafioka.composerecview.model

data class TvShow(
    val id: Int,
    val name: String,
    val year: Int,
    val rating: Double,
    val imageId: Int,
    val overview: String
)