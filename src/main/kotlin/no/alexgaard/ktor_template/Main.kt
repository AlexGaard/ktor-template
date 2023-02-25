package no.alexgaard.ktor_template

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import no.alexgaard.ktor_template.application.createApplication
import no.alexgaard.ktor_template.application.startApplication
import no.alexgaard.ktor_template.config.ApplicationConfig

fun main () {
	val config = ConfigLoaderBuilder.default()
		.addResourceSource("/application-config.yml")
		.build()
		.loadConfigOrThrow<ApplicationConfig>()

	val app = createApplication(config)

	app.server.start(true)

//	startApplication(config)

}