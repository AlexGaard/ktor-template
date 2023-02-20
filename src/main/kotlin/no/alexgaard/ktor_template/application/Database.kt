package no.alexgaard.ktor_template.application

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import no.alexgaard.ktor_template.config.ApplicationConfig
import org.flywaydb.core.Flyway
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.slf4j.LoggerFactory
import javax.sql.DataSource

object Database {

	private val log = LoggerFactory.getLogger(javaClass)

	fun migrateDb(dataSource: DataSource) {
		log.info("Applying database migrations...")

		Flyway.configure()
			.dataSource(dataSource)
			.load()
			.migrate()
	}

	fun createJdbi(dataSource: DataSource): Jdbi {
		return Jdbi.create(dataSource).installPlugin(KotlinPlugin())
	}

	fun createDataSource(config: ApplicationConfig.Database): DataSource {
		val hikariConfig = HikariConfig()
		hikariConfig.jdbcUrl = config.jdbcUrl
		hikariConfig.username = config.username
		hikariConfig.password = config.password
		hikariConfig.minimumIdle = config.minimumIdle
		hikariConfig.maximumPoolSize = config.maximumPoolSize
		return HikariDataSource(hikariConfig)
	}

}