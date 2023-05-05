import java.util.*

data class Release(
    val name: String,
    val link: String,
    val release: Date,
    val peakPlayers: Int,
    val positiveReviews: Int,
    val negativeReviews: Int,
    val totalReviews: Int,
    val rating: String
    )