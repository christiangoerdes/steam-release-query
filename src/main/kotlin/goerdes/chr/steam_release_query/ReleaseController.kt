package goerdes.chr.steam_release_query

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ReleaseController(val service: ReleaseService) {

    @QueryMapping
    fun releases() = service.getAll()

    @QueryMapping
    fun releasesByName(@Argument name: String) = service.getByName(name)

    @QueryMapping
    fun releasesByRating(@Argument rating: String) = service.getByRating(rating)

    @QueryMapping
    fun releasesBy(@Argument field: String?) = service.releasesBy(field)
}