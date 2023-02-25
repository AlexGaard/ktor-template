package no.alexgaard.ktor_template.controller

import io.kotest.matchers.shouldBe
import no.alexgaard.ktor_template.test_utils.IntegrationTest
import org.junit.jupiter.api.Test

class GreetingControllerTest : IntegrationTest() {

	@Test
	fun `should return greeting with name`() {
		val res = sendRequest(
			method = "GET",
			path = "/api/v1/greeting?name=Test",
		)

		res.body?.string() shouldBe "Hello Test"
	}

}