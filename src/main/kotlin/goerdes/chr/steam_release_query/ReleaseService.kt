package goerdes.chr.steam_release_query

import org.springframework.stereotype.Service
import kotlin.reflect.full.memberProperties

@Service
class ReleaseService(val provider: ReleaseJsonProvider) {
    fun getAll(pageInput: PageInput?): PaginatedData {
        val tmp = pageInput ?: PageInput()
        return PaginatedData(
            PageInfo(
                tmp.page,
                tmp.size,
                provider.query("$[*]", Array<Release>::class.java).toList().size
            ),
            provider.query("$[*]", Array<Release>::class.java).toList().subList(
                (tmp.page-1)*tmp.size,
                tmp.page*tmp.size
            )
        )
    }

    fun getByName(title: String): Any {
        return provider.query("$[?(@.title =~ /.*$title.*/i)]", Array<Release>::class.java).toList()
    }

    fun getByRating(rating: String): Any {
        return provider.query("$[?(@.rating =~ /$rating.*/i)]", Array<Release>::class.java)
            .toList()
            .sortedByDescending{
                it.rating
            }
    }

    fun releasesBy(field: ReleaseFields): Any {
        return provider.query("$[*]", Array<Release>::class.java)
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
            }
    }
    private inline fun <reified T : Any> T.asMap() : Map<String, Any?> {
        val props = T::class.memberProperties.associateBy { it.name }
        return props.keys.associateWith { props[it]?.get(this) }
    }
}