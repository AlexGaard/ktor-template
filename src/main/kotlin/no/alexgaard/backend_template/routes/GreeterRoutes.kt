package no.alexgaard.backend_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.alexgaard.backend_template.service.GreeterService

fun registerGreeterRoutes(
	routing: Routing,
	greeterService: GreeterService,
) {
	routing {
		get("/api/v1/greeting") {
			call.respondText(greeterService.getGreeting())
		}
	}
}
