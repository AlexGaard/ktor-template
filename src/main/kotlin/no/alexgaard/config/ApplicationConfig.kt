package no.alexgaard.config

data class ApplicationConfig(
	val server: ServerConfig,
)

data class ServerConfig(
	val port: Int = 8080,
	val host: String = "127.0.0.1",
	val wait: Boolean = true
)
