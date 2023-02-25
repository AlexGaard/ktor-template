package no.alexgaard.ktor_template.application

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.routes.registerGreeterRoutes
import no.alexgaard.ktor_template.routes.registerMetricRoutes
import no.alexgaard.ktor_template.routes.registerUserRoutes
import org.koin.core.Koin
import org.koin.dsl.koinApplication

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
		install(MicrometerMetrics) { registry = koin.get<PrometheusMeterRegistry>() }

		routing {
			registerMetricRoutes(koin.get())
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
