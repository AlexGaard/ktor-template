package no.alexgaard.ktor_template.repository

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.mapTo

class UserRepository(
	private val jdbi: Jdbi
) {

	fun createUser(name: String): UserDbo {
		return jdbi.withHandle<UserDbo, Exception> {
			it.createQuery("INSERT INTO users (name) VALUES (:name) RETURNING *")
				.bind("name", name)
				.mapTo<UserDbo>()
				.first()
		}
	}

	fun getAllUsers(): List<UserDbo> {
		return jdbi.withHandle<List<UserDbo>, Exception> {
			it.createQuery("select * from users").mapTo<UserDbo>().toList()
		}
	}

}

data class UserDbo(
	val id: Int,
	val name: String,
)
