package goerdes.chr.steam_release_query
import java.util.*

data class Release(
    var name: String = "",
    var link: String = "",
    var release: String = "",
    var peakPlayers: Int = 0,
    var positiveReviews: Int = 0,
    var negativeReviews: Int = 0,
    var totalReviews: Int = 0,
    var rating: String = ""
)