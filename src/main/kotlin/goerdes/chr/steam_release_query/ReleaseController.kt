package goerdes.chr.steam_release_query

import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ReleaseController(val service: ReleaseService) {

    @QueryMapping
    fun releases(@Argument pageInput: PageInput?) = service.getAll(pageInput)

    @QueryMapping
    fun releasesByName(@Argument title: String, @Argument pageInput: PageInput?) = service.getByName(title, pageInput)

    @QueryMapping
    fun releasesByRating(@Argument rating: String,@Argument pageInput: PageInput?) = service.getByRating(rating, pageInput)

    @QueryMapping
    fun releasesBy(@Argument field: ReleaseFields, @Argument pageInput: PageInput?) = service.releasesBy(field, pageInput)
}