package no.alexgaard.ktor_template.client.dummy_json

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import no.alexgaard.ktor_template.util.rest.JsonUtils.jsonMapper
import no.alexgaard.ktor_template.util.rest.baseClient
import okhttp3.OkHttpClient
import okhttp3.Request

class DummyJsonClientImpl(
	private val baseUrl: String,
	private val bearerTokenProvider: () -> String,
	private val client: OkHttpClient = baseClient()
) : DummyJsonClient {

	override fun getAllUsers(): List<User> {
		val request = Request.Builder()
			.url("$baseUrl/users")
			.addHeader("Authorization", "Bearer ${bearerTokenProvider.invoke()}")
			.get()
			.build()

		val resp = client.newCall(request).execute()
		return resp.body?.string()?.let { body ->
			jsonMapper.decodeFromString<GetAllUsers.Response>(body)
				.users
				.map { User(it.id, it.firstName, it.lastName) }
		} ?: throw IllegalArgumentException("")
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