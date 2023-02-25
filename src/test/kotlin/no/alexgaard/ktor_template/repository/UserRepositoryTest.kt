package no.alexgaard.ktor_template.repository

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import no.alexgaard.ktor_template.test_utils.IntegrationTest
import no.alexgaard.ktor_template.test_utils.database.DatabaseUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRepositoryTest : IntegrationTest() {

	private val userRepository = dependencies.get<UserRepository>()

	@BeforeEach
	fun cleanDatabase() {
		DatabaseUtils.cleanSchema(dependencies.get())
	}

	@Test
	fun `getAllUsers - should get all users`() {
		val user1 = "Test1"
		val user2 = "Test2"

		userRepository.createUser(user1)
		userRepository.createUser(user2)

		val users = userRepository.getAllUsers()

		users shouldHaveSize 2
		users.any { it.name == user1 } shouldBe true
		users.any { it.name == user2 } shouldBe true
	}

}