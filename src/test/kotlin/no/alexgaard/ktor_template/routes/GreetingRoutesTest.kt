package no.alexgaard.ktor_template.routes

import io.kotest.matchers.shouldBe
import no.alexgaard.ktor_template.test_utils.IntegrationTest
import org.junit.jupiter.api.Test

class GreetingRoutesTest : IntegrationTest() {

	@Test
	fun `should return greeting with name`() {
		val res = sendRequest(
			method = "GET",
			path = "/api/v1/greeting?name=Test",
		)

		res.statusCode shouldBe 200
		res.body shouldBe "Hello Test"
	}

}