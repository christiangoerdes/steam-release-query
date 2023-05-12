package goerdes.chr.steam_release_query
import java.util.*

data class Release(
    var title: String = "",
    var link: String = "",
    var release: String = "",
    var peakPlayers: Int = 0,
    var positiveReviews: Int = 0,
    var negativeReviews: Int = 0,
    var totalReviews: Int = 0,
    var rating: String = ""
)

enum class ReleaseFields(val asc: Boolean) {
    title(true),
    link(true),
    release(false),
    peakPlayers(false),
    positiveReviews(false),
    negativeReviews(false),
    totalReviews(false),
    rating(false)
}