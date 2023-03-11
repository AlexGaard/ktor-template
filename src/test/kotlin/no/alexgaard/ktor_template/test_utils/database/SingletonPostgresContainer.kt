package no.alexgaard.ktor_template.test_utils.database

import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

object SingletonPostgresContainer {

	private var postgresContainer: PostgresContainer? = null

	fun dataSource(): DataSource {
		return getOrCreateContainer().dataSource()
	}

	fun container(): PostgreSQLContainer<Nothing> {
		return getOrCreateContainer().container()
	}

	private fun getOrCreateContainer(): PostgresContainer {
		if (postgresContainer == null) {
			val container = PostgresContainer()
			container.start()
			postgresContainer = container
		}

		return postgresContainer!!
	}

}