package no.alexgaard.ktor_template.util.rest

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JsonUtils {

	val jsonMapper: ObjectMapper = ObjectMapper()
		.registerKotlinModule()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

	inline fun <reified T> fromJsonString(jsonStr: String): T {
		return jsonMapper.readValue(jsonStr, T::class.java)
	}

	fun toJsonString(any: Any): String {
		return jsonMapper.writeValueAsString(any)
	}

}