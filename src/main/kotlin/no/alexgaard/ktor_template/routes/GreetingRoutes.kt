package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.alexgaard.ktor_template.service.GreetingService

fun Route.registerGreetingRoutes(greetingService: GreetingService) =
	route("/api/v1") {
		get("/greeting") {
			val name = call.parameters["name"] ?: throw IllegalArgumentException("name is missing")
			call.respondText(greetingService.getGreeting(name))
		}
	}
