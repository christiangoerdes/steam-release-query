package goerdes.chr.steam_release_query
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class ReleaseJsonProvider(@Value("classpath:data/releases.json") private val jsonResource: Resource) {

    lateinit var jsonData: JsonNode
    private val objectMapper = ObjectMapper()

    @PostConstruct
    fun init() {
        jsonData = objectMapper.readTree(jsonResource.inputStream)
    }

    fun <T> query(jsonPathExpression: String, targetType: Class<T>): T {
        return JsonPath.parse(jsonData.toString()).read(jsonPathExpression, targetType)
    }
}