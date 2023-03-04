package no.alexgaard.ktor_template.test_utils

import io.ktor.server.application.*
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.test_utils.FreePortFinder.findFreePort
import no.alexgaard.ktor_template.test_utils.database.SingletonPostgresContainer
import no.alexgaard.ktor_template.util.Secret

object TestApplicationConfig {

	fun createTestConfig(): ApplicationConfig {
		val container = SingletonPostgresContainer.container()

		return ApplicationConfig(
			ApplicationConfig.Server(
				port = findFreePort()
			),
			ApplicationConfig.Database(
				username = container.username,
				password = Secret(container.password),
				jdbcUrl = container.jdbcUrl,
			),
			ApplicationConfig.DummyJsonClient(
				baseUrl = "https://dummyjson.com"
			)
		)
	}

}