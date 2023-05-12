package goerdes.chr.steam_release_query

import org.springframework.stereotype.Service
import kotlin.reflect.full.memberProperties

@Service
class ReleaseService(val provider: ReleaseJsonProvider) {
    fun getAll(): List<Release> {
        return provider.query("$[*]", Array<Release>::class.java).toList()
    }

    fun getByName(name: String): Any {
        return provider.query("$[?(@.name =~ /.*$name.*/i)]", Array<Release>::class.java).toList()
    }

    fun getByRating(rating: String): Any {
        return provider.query("$[?(@.rating =~ /$rating.*/i)]", Array<Release>::class.java)
            .toList()
            .sortedByDescending{
                it.rating
            }
    }

    fun releasesBy(field: String?): Any {
        return provider.query("$[*]", Array<Release>::class.java)
            .toList()
            .sortedByDescending {
                if (field != null) {
                    it.asMap()[field].toString()
                }else{
                    it.rating
                }
            }
    }
    private inline fun <reified T : Any> T.asMap() : Map<String, Any?> {
        val props = T::class.memberProperties.associateBy { it.name }
        return props.keys.associateWith { props[it]?.get(this) }
    }
}