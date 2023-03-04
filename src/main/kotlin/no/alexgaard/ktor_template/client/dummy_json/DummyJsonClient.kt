package no.alexgaard.ktor_template.client.dummy_json

import no.alexgaard.ktor_template.util.rest.ApiResult

interface DummyJsonClient {

	fun getAllUsers(): ApiResult<List<User>>

}

data class User(
	val id: Int,
	val firstName: String,
	val lastName: String
)