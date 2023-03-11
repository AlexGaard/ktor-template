package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.alexgaard.ktor_template.service.GreetingService

class GreetingRoutes(
	private val greetingService: GreetingService
) {

	fun register(routing: Routing) {
		routing.route("/api/v1") {
			getUsers()
		}
	}

	private fun Route.getUsers() {
		get("/greeting") {
			val name = call.parameters["name"] ?: throw IllegalArgumentException("name is missing")
			call.respondText(greetingService.getGreeting(name))
		}
	}

}
