package no.alexgaard.ktor_template

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import no.alexgaard.ktor_template.application.Application
import no.alexgaard.ktor_template.config.ApplicationConfig
import org.slf4j.LoggerFactory

fun main() = startApplication()

fun startApplication() {
	val log = LoggerFactory.getLogger("Main")

	log.info("Starting application...")

	val config = ConfigLoaderBuilder.default()
		.addResourceSource("/application-config.yml")
		.build()
		.loadConfigOrThrow<ApplicationConfig>()

	log.info("Loaded configuration: {}", config)

	val app = Application.create(config)

	app.server.start()
}

