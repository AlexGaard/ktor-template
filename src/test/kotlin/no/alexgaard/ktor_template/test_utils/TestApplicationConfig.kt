package no.alexgaard.ktor_template.test_utils

import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.test_utils.FreePortFinder.findFreePort
import no.alexgaard.ktor_template.test_utils.database.SingletonPostgresContainer

object TestApplicationConfig {

	fun createTestConfig(): ApplicationConfig {
		val container = SingletonPostgresContainer.container()

		return ApplicationConfig(
			ApplicationConfig.Server(
				wait = false,
				port = findFreePort()
			),
			ApplicationConfig.Database(
				username = container.username,
				password = container.password,
				jdbcUrl = container.jdbcUrl,
			)
		)
	}

}