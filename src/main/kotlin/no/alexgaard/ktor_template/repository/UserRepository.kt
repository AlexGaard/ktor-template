package no.alexgaard.ktor_template.repository

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.mapTo

class UserRepository(
	private val jdbi: Jdbi
) {

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
