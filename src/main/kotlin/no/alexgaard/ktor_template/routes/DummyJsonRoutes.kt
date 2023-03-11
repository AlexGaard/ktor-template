package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import no.alexgaard.ktor_template.client.dummy_json.DummyJsonClient

class DummyJsonRoutes(
	private val dummyJsonClient: DummyJsonClient
) {

	fun register(routing: Routing) {
		routing.route("/api/v1/dummy") {
			getUsers()
		}
	}

	private fun Route.getUsers() {
		get("/users") {
			val users = dummyJsonClient.getAllUsers()
				.getOrThrow()
				.map { UserDto(it.id, it.firstName, it.lastName) }

			call.respond(users)
		}
	}

	@Serializable
	data class UserDto(
		val id: Int,
		val firstName: String,
		val lastName: String
	)

}
