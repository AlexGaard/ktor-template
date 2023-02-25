package no.alexgaard.ktor_template.test_utils

import no.alexgaard.ktor_template.application.Application
import no.alexgaard.ktor_template.application.createApplication
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.test_utils.database.DatabaseUtils
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.jupiter.api.BeforeEach
import org.koin.core.Koin

open class IntegrationTest {

	private val application: Application

	private val config = TestApplicationConfig.createTestConfig()

	private val serverUrl = getServerUrl(config)

	private val client = OkHttpClient()

	val dependencies: Koin

	init {
		application = createApplication(config)
		application.server.start(wait = false)
		dependencies = application.dependencies
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

	private fun getServerUrl(config: ApplicationConfig): String {
		return "http://${config.server.host}:${config.server.port}"
	}

}