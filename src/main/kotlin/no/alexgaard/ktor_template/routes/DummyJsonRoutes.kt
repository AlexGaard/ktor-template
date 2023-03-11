package no.alexgaard.ktor_template.routes

import io.javalin.Javalin
import io.javalin.http.Context
import no.alexgaard.ktor_template.client.dummy_json.DummyJsonClient

class DummyJsonRoutes(
	private val dummyJsonClient: DummyJsonClient
) {

	fun register(app: Javalin) {
		getUsers(app)
	}

	private fun getUsers(app: Javalin) {
		app.get("/api/v1/dummy/users") { ctx ->
			val users = dummyJsonClient.getAllUsers()
				.getOrThrow()
				.map { UserDto(it.id, it.firstName, it.lastName) }

			ctx.json(users)
		}
	}

	data class UserDto(
		val id: Int,
		val firstName: String,
		val lastName: String
	)

}
