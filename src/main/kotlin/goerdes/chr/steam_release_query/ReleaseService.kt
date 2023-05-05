package goerdes.chr.steam_release_query

import Release
import org.springframework.stereotype.Service

@Service
class ReleaseService(val provider: ReleaseJsonProvider) {
    fun getAll() = provider.query("$", List::class.java)
}