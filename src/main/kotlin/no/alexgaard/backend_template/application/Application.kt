package no.alexgaard.backend_template.application

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import no.alexgaard.backend_template.config.ApplicationConfig
import no.alexgaard.backend_template.routes.registerGreeterRoutes
import org.koin.dsl.koinApplication


fun startApplication(config: ApplicationConfig) {
	val koin = koinApplication {
		modules(ApplicationModules.createModules(config))
	}.koin

	val server = embeddedServer(
		factory = Netty,
		port = config.server.port,
		host = config.server.host,
	) {
		routing {
			registerGreeterRoutes(this, koin.get())
		}
	}

	server.start(wait = config.server.wait)
}

