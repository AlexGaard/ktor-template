package no.alexgaard.ktor_template.test_utils

import no.alexgaard.ktor_template.application.createApplication
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

open class IntegrationTest {

	companion object {
		private val config = TestApplicationConfig.createTestConfig()

		private val serverUrl = "http://${config.server.host}:${config.server.port}"

		private val client = OkHttpClient()

		private val application = createApplication(config)

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
	): Response {
		val request = Request.Builder()
			.url("${serverUrl}${path}")
			.headers(headers.toHeaders())
			.method(method, body?.toRequestBody(contentType))
			.build()

		return client.newCall(request).execute()
	}

}