package no.alexgaard

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import no.alexgaard.application.startApplication
import no.alexgaard.config.ApplicationConfig

fun main () {
	val config = ConfigLoaderBuilder.default()
		.addResourceSource("/application-config.yml")
		.build()
		.loadConfigOrThrow<ApplicationConfig>()

	startApplication(config)
}