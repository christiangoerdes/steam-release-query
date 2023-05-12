package goerdes.chr.steam_release_query

import org.springframework.stereotype.Service
import kotlin.reflect.full.memberProperties

@Service
class ReleaseService(val provider: ReleaseJsonProvider) {
    fun getAll(pageInput: PageInput?): PaginatedData {
        return paginatedList(provider.query("$[*]", Array<Release>::class.java).toList(), pageInput)
    }

    private fun paginatedList(list: List<Release>, pageInput: PageInput?): PaginatedData {
        val tmp = pageInput ?: PageInput()
        return list.let {
            PaginatedData(
                PageInfo(
                    tmp.page,
                    tmp.size,
                    it.size
                ),
                it.subList(
                    (tmp.page - 1) * tmp.size,
                    tmp.page * tmp.size
                )
            )
        }
    }


    fun getByName(title: String, pageInput: PageInput?): Any {
        return paginatedList(provider.query("$[?(@.title =~ /.*$title.*/i)]", Array<Release>::class.java).toList(), pageInput)
    }

    fun getByRating(rating: String, pageInput: PageInput?): Any {
        return paginatedList(
            provider.query("$[?(@.rating =~ /$rating.*/i)]", Array<Release>::class.java)
            .toList()
            .sortedByDescending{
                it.rating
            },
            pageInput
        )
    }

    fun releasesBy(field: ReleaseFields, pageInput: PageInput?): Any {
        return paginatedList(
            provider.query("$[*]", Array<Release>::class.java)
            .toList()
            .let{ x ->
                if(field.asc) {
                    x.sortedBy {
                        it.asMap()[field.name].toString()
                    }
                }else{
                    x.sortedByDescending {
                        it.asMap()[field.name].toString()
                    }
                }
            },
            pageInput
        )
    }
    private inline fun <reified T : Any> T.asMap() : Map<String, Any?> {
        val props = T::class.memberProperties.associateBy { it.name }
        return props.keys.associateWith { props[it]?.get(this) }
    }
}