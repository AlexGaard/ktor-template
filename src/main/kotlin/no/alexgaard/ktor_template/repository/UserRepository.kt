package no.alexgaard.ktor_template.repository

import no.alexgaard.ktor_template.util.handle
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.mapTo

class UserRepository(
	private val jdbi: Jdbi
) {

	fun createUser(name: String): UserDbo = jdbi.handle {
		it.createQuery("INSERT INTO users (name) VALUES (:name) RETURNING *")
			.bind("name", name)
			.mapTo<UserDbo>()
			.first()
	}

	fun getAllUsers(): List<UserDbo> = jdbi.handle {
		it.createQuery("SELECT * FROM users")
			.mapTo<UserDbo>()
			.toList()
	}

}

data class UserDbo(
	val id: Int,
	val name: String,
)
