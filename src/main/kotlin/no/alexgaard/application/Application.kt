package no.alexgaard.application

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.alexgaard.config.ApplicationConfig

fun startApplication(config: ApplicationConfig) {
	embeddedServer(
		factory = Netty,
		port = config.server.port,
		host = config.server.host,
		module = Application::module
	).start(wait = config.server.wait)
}

fun Application.module() {
	routing {
		get("/") {
			call.respondText("Hello World!")
		}
	}
}

