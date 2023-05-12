package goerdes.chr.steam_release_query

data class PaginatedData (
    val pageInfo: PageInfo,
    val items: List<Release>
)

data class PageInfo(
    val page: Int,
    val size: Int,
    val total: Int
)

data class PageInput(
    var page: Int = 1,
    var size: Int = 10
)