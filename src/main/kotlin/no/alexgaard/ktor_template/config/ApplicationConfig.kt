package no.alexgaard.ktor_template.config

import no.alexgaard.ktor_template.util.Secret

data class ApplicationConfig(
	val server: Server,
	val database: Database,
	val dummyJsonClient: DummyJsonClient
) {
	data class Server(
		val port: Int,
		val host: String,
	)

	data class Database(
		val jdbcUrl: String,
		val username: String,
		val password: Secret,
	)

	data class DummyJsonClient(
		val baseUrl: String
	)
}
