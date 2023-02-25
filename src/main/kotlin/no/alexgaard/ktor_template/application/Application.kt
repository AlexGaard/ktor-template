package no.alexgaard.ktor_template.application

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.routes.registerGreeterRoutes
import no.alexgaard.ktor_template.routes.registerUserRoutes
import org.koin.core.Koin
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
		install(CallLogging)
		install(Compression) { gzip() }
		install(ContentNegotiation) { json() }

		routing {
			registerGreeterRoutes(koin.get())
			registerUserRoutes(koin.get())
		}
	}

	server.start(wait = config.server.wait)
}

fun createApplication(config: ApplicationConfig): Application {
	val koin = koinApplication {
		modules(ApplicationModule.createModule(config))
	}.koin

	Database.migrateDb(koin.get())

	val server = embeddedServer(
		factory = Netty,
		port = config.server.port,
		host = config.server.host,
	) {
		install(CallLogging)
		install(Compression) { gzip() }
		install(ContentNegotiation) { json() }

		routing {
			registerGreeterRoutes(koin.get())
			registerUserRoutes(koin.get())
		}
	}

	return Application(server, koin)
}

data class Application(
	val server: NettyApplicationEngine,
	val dependencies: Koin
)
