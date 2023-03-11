package no.alexgaard.ktor_template.routes

import io.javalin.Javalin
import io.javalin.http.Context
import no.alexgaard.ktor_template.service.UserService

class UserRoutes(
	private val userService: UserService
) {

	fun register(app: Javalin) {
		getAllUsers(app)
		createUser(app)
	}

	private fun getAllUsers(app: Javalin) {
		app.get("/api/v1/user") { ctx ->
			val users = userService.getAllUsers()
				.map { UserDto(it.id, it.name) }

			ctx.json(users)
		}
	}

	private fun createUser(app: Javalin) {
		app.post("/api/v1/user") { ctx ->
			val request = ctx.bodyAsClass(CreateUserRequest::class.java)

			val newUser = userService.createUser(request.name)
				.let { UserDto(it.id, it.name) }

			ctx.json(newUser)
		}
	}

	data class UserDto(
		val id: Int,
		val name: String,
	)

	data class CreateUserRequest(
		val name: String
	)

}
