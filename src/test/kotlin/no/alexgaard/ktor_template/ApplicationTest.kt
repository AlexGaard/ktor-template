package no.alexgaard.ktor_template

import no.alexgaard.ktor_template.application.startApplication
import no.alexgaard.ktor_template.config.ApplicationConfig
import org.junit.jupiter.api.Test


class ApplicationTest {

	@Test
	fun `application should boot`() {
		val config = ApplicationConfig(
			ApplicationConfig.Server().copy(wait = false)
		)

		startApplication(config)
	}

}