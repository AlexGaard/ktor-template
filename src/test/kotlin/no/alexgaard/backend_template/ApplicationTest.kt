package no.alexgaard.backend_template

import no.alexgaard.backend_template.application.startApplication
import no.alexgaard.backend_template.config.ApplicationConfig
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