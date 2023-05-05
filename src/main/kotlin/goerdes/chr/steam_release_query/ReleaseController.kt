package goerdes.chr.steam_release_query

import Release
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ReleaseController {



    @QueryMapping
    fun releases(): Iterable<Release>? {
        return null;
    }
}