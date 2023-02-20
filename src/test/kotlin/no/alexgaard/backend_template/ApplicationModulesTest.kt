package no.alexgaard.backend_template

import no.alexgaard.backend_template.application.ApplicationModules
import no.alexgaard.backend_template.config.ApplicationConfig
import org.junit.jupiter.api.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class ApplicationModulesTest {

	@OptIn(KoinExperimentalAPI::class)
	@Test
	fun `application modules should resolve correctly`() {
		val config = ApplicationConfig(ApplicationConfig.Server())

		ApplicationModules.createModules(config).verify()
	}

}