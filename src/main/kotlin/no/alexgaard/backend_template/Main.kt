package no.alexgaard.backend_template

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import no.alexgaard.backend_template.application.startApplication
import no.alexgaard.backend_template.config.ApplicationConfig

fun main () {
	val config = ConfigLoaderBuilder.default()
		.addResourceSource("/application-config.yml")
		.build()
		.loadConfigOrThrow<ApplicationConfig>()

	startApplication(config)
}