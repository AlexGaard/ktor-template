package no.alexgaard.ktor_template

import no.alexgaard.ktor_template.application.startApplication
import no.alexgaard.ktor_template.test_utils.TestApplicationConfig
import org.junit.jupiter.api.Test


class ApplicationTest {

	@Test
	fun `application should boot`() {
		startApplication(TestApplicationConfig.createTestConfig())
	}

}