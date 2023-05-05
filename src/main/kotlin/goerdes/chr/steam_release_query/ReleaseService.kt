package goerdes.chr.steam_release_query

import org.springframework.stereotype.Service

@Service
class ReleaseService(val provider: ReleaseJsonProvider) {
    fun getAll(): List<Release> {
        return provider.query("$[*]", Array<Release>::class.java).toList()
    }
}