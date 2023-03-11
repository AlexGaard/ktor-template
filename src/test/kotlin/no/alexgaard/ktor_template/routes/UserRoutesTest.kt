package no.alexgaard.ktor_template.routes

import io.kotest.matchers.shouldBe
import no.alexgaard.ktor_template.repository.UserRepository
import no.alexgaard.ktor_template.test_utils.IntegrationTest
import no.alexgaard.ktor_template.test_utils.database.DatabaseUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRoutesTest : IntegrationTest() {

	private val userRepository = dependencies.get<UserRepository>()
	
	@BeforeEach
	fun cleanDatabase() {
		DatabaseUtils.cleanSchema(dependencies.get())
	}

	@Test
	fun `should create new user`() {
		val res = sendRequest(
			path = "/api/v1/user",
			method = "POST",
			body = """
				{ "name": "Test" }
			""".trimIndent()
		)

		res.statusCode shouldBe 200

		val allUsers = userRepository.getAllUsers()

		allUsers.size shouldBe 1
		allUsers[0].id shouldBe 1
		allUsers[0].name shouldBe "Test"
	}

	@Test
	fun `should get all users`() {
		userRepository.createUser("Test1")
		userRepository.createUser("Test2")

		val res = sendRequest(
			method = "GET",
			path = "/api/v1/user"
		)

		val expectedJson = """
			[{"id":1,"name":"Test1"},{"id":2,"name":"Test2"}]
		""".trimIndent()

		res.statusCode shouldBe 200
		res.body shouldBe expectedJson
	}

}