package no.alexgaard.ktor_template.config

data class ApplicationConfig(
	val server: Server,
	val database: Database
) {
	data class Server(
		val port: Int = 8080,
		val host: String = "127.0.0.1",
		val wait: Boolean = true
	)

	data class Database(
		val jdbcUrl: String,
		val username: String,
		val password: String,
		val minimumIdle: Int = 1,
		val maximumPoolSize: Int = 10
	)
}
