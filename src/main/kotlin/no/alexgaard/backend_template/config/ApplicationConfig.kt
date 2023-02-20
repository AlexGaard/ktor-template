package no.alexgaard.backend_template.config

data class ApplicationConfig(
	val server: Server,
) {
	data class Server(
		val port: Int = 8080,
		val host: String = "127.0.0.1",
		val wait: Boolean = true
	)
}
