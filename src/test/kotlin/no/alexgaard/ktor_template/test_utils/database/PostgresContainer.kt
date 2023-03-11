package no.alexgaard.ktor_template.test_utils.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import no.alexgaard.ktor_template.test_utils.database.DatabaseUtils.isValid
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy
import javax.sql.DataSource

class PostgresContainer {

	private val postgresImageName = "postgres:14-alpine"

	private val databaseTimeZone = "Europe/Oslo"

	private val postgresContainer: PostgreSQLContainer<Nothing> = createContainer()

	private var containerDataSource: DataSource? = null

	fun start() {
		postgresContainer.start()
	}

	fun stop() {
		postgresContainer.stop()
	}

	fun dataSource(): DataSource {
		if (!postgresContainer.isRunning) {
			throw IllegalStateException("Database must be started before a data source can be created")
		}

		if (containerDataSource == null || !isValid(containerDataSource!!)) {
			containerDataSource = createDataSource(postgresContainer)
		}

		return containerDataSource!!
	}

	fun container(): PostgreSQLContainer<Nothing> {
		return postgresContainer
	}

	private fun createContainer(): PostgreSQLContainer<Nothing> {
		val container = PostgreSQLContainer<Nothing>(postgresImageName)
		container.addEnv("TZ", databaseTimeZone)
		return container.waitingFor(HostPortWaitStrategy())
	}

	private fun createDataSource(container: PostgreSQLContainer<Nothing>): DataSource {
		val config = HikariConfig()

		config.jdbcUrl = container.jdbcUrl
		config.username = container.username
		config.password = container.password
		config.minimumIdle = 1
		config.maximumPoolSize = 3

		return HikariDataSource(config)
	}

}