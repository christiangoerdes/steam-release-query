package goerdes.chr.steam_release_query
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import com.jayway.jsonpath.Option
import jakarta.annotation.PostConstruct
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class ReleaseJsonProvider(private val objectMapper: ObjectMapper) {

    private lateinit var jsonData: Any

    @PostConstruct
    private fun loadJsonData() {
        try {
            val jsonFile = ClassPathResource(JSON_FILE_NAME).file
            jsonData = objectMapper.readValue(jsonFile, Any::class.java)
        } catch (e: IOException) {
            throw RuntimeException("Failed to read data from JSON file", e)
        }
    }

    fun <T> query(jsonPathExpression: String, returnType: Class<T>): T {
        val configuration = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS)
        return JsonPath.using(configuration).parse(jsonData).read(jsonPathExpression, returnType)
    }

    companion object {
        private const val JSON_FILE_NAME = "data/releases.json"
    }
}