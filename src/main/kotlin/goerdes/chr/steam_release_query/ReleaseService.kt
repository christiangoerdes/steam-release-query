package goerdes.chr.steam_release_query

import org.springframework.stereotype.Service

@Service
class ReleaseService(val provider: ReleaseJsonProvider) {
    fun getAll(): List<Release> {
        return provider.query("$[*]", Array<Release>::class.java).toList()
    }

    fun getByName(name: String): Any {
        return provider.query("$[?(@.name =~ /.*$name.*/i)]", Array<Release>::class.java).toList()
    }
}