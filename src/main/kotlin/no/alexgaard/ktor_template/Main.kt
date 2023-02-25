package no.alexgaard.ktor_template

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import no.alexgaard.ktor_template.application.createApplication
import no.alexgaard.ktor_template.config.ApplicationConfig
import org.slf4j.LoggerFactory

fun main () {
	val config = ConfigLoaderBuilder.default()
		.addResourceSource("/application-config.yml")
		.build()
		.loadConfigOrThrow<ApplicationConfig>()

	val log = LoggerFactory.getLogger("Main")
	log.info("Running with config: {}", config)

	val app = createApplication(config)

	app.server.start(true)
}