package no.alexgaard.ktor_template.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import no.alexgaard.ktor_template.service.UserService

fun Route.registerUserRoutes(userService: UserService) =
	route("/api/v1") {
		get("/user") {
			val users = userService.getAllUsers()
				.map { UserDto(it.id, it.name) }

			call.respond(users)
		}

		post("/user") {
			val request = call.receive<CreateUserDto>()

			userService.createUser(request.name)

			call.respond(HttpStatusCode.OK)
		}
	}

@Serializable
data class UserDto(
	val id: Int,
	val name: String,
)

@Serializable
data class CreateUserDto(
	val name: String
)
