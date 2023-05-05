import java.time.LocalDate

data class Game(
    val name: String,
    val link: String,
    val release: LocalDate,
    val peakPlayers: Int,
    val positiveReviews: Int,
    val negativeReviews: Int,
    val totalReviews: Int,
    val rating: String
    )