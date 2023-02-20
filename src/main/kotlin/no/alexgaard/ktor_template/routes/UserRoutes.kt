package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.alexgaard.ktor_template.service.UserService

fun registerUserRoutes(
	routing: Routing,
	userService: UserService,
) {
	routing {
		get("/api/v1/user") {
			call.respondText(userService.getAllUsers().toString())
		}
	}
}
