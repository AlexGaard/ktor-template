package no.alexgaard.ktor_template.client.dummy_json

interface DummyJsonClient {

	fun getAllUsers(): List<User>

}

data class User(
	val id: Int,
	val firstName: String,
	val lastName: String
)