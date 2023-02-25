package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.alexgaard.ktor_template.service.GreeterService

fun Route.registerGreeterRoutes(greeterService: GreeterService) =
	route("/api/v1") {
		get("/greeting") {
			val name = call.parameters["name"] ?: throw IllegalArgumentException("name is missing")
			call.respondText(greeterService.getGreeting(name))
		}
	}
