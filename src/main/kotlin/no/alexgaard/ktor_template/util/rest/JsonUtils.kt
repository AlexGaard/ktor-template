package no.alexgaard.ktor_template.util.rest

import kotlinx.serialization.json.Json

object JsonUtils {

	val jsonMapper = Json {
		ignoreUnknownKeys = true
	}

}