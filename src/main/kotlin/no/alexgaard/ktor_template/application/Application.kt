package no.alexgaard.ktor_template.application

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.routes.registerGreeterRoutes
import no.alexgaard.ktor_template.routes.registerUserRoutes
import org.koin.dsl.koinApplication


fun startApplication(config: ApplicationConfig) {
	val koin = koinApplication {
		modules(ApplicationModule.createModule(config))
	}.koin

	Database.migrateDb(koin.get())

	val server = embeddedServer(
		factory = Netty,
		port = config.server.port,
		host = config.server.host,
	) {
		routing {
			registerGreeterRoutes(this, koin.get())
			registerUserRoutes(this, koin.get())
		}
	}

	server.start(wait = config.server.wait)
}

