package no.alexgaard.ktor_template.config

import no.alexgaard.ktor_template.util.Secret

data class ApplicationConfig(
	val server: Server,
	val database: Database
) {
	data class Server(
		val port: Int = 8080,
		val host: String = "127.0.0.1",
	)

	data class Database(
		val jdbcUrl: String,
		val username: String,
		val password: Secret,
		val minimumIdle: Int = 1,
		val maximumPoolSize: Int = 10,
	)

}
