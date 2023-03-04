package no.alexgaard.ktor_template.test_utils

import no.alexgaard.ktor_template.application.createApplication
import no.alexgaard.ktor_template.util.rest.RawResponse
import no.alexgaard.ktor_template.util.rest.request
import no.alexgaard.ktor_template.util.rest.sendRaw
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType

open class IntegrationTest {

	companion object {
		private val config = TestApplicationConfig.createTestConfig()

		private val serverUrl = "http://${config.server.host}:${config.server.port}"

		private val application = createApplication(config)

		private val client = OkHttpClient()

		val dependencies = application.dependencies

		init {
			application.server.start(wait = false)
		}
	}

	fun sendRequest(
		method: String,
		path: String,
		headers: Map<String, String> = emptyMap(),
		body: String? = null,
		contentType: MediaType = "application/json".toMediaType()
	): RawResponse {
		val url = "${serverUrl}${path}"

		return client.sendRaw(request(
			method = method,
			url = url,
			headers = headers,
			body = body,
			contentType = contentType
		))
	}

}