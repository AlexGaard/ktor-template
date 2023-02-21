package no.alexgaard.ktor_template.repository

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import no.alexgaard.ktor_template.test_utils.TestApplication
import org.junit.jupiter.api.Test

class UserRepositoryTest : TestApplication() {

	private val userRepository = koin.get<UserRepository>()

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