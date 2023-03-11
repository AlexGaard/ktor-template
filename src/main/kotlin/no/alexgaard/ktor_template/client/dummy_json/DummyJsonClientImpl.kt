package no.alexgaard.ktor_template.client.dummy_json

import kotlinx.serialization.Serializable
import no.alexgaard.ktor_template.util.rest.*
import okhttp3.OkHttpClient

class DummyJsonClientImpl(
	private val baseUrl: String,
	private val client: OkHttpClient = baseClient()
) : DummyJsonClient {

	override fun getAllUsers(): ApiResult<List<User>> {
		val req = request(
			method = "GET",
			url = "$baseUrl/users",
		)

		return client.sendRequest<GetAllUsers.Response>(req)
			.map { data -> data.users.map { User(it.id, it.firstName, it.lastName) } }
	}

	object GetAllUsers {
		@Serializable
		data class Response(
			val users: List<User>
		) {
			@Serializable
			data class User(
				val id: Int,
				val firstName: String,
				val lastName: String
			)
		}
	}

}