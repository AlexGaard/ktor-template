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
import io.micrometer.prometheus.PrometheusMeterRegistry
import no.alexgaard.ktor_template.application.ApplicationModule.resolveDependencies
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.routes.DummyJsonRoutes
import no.alexgaard.ktor_template.routes.GreetingRoutes
import no.alexgaard.ktor_template.routes.MetricRoutes
import no.alexgaard.ktor_template.routes.UserRoutes
import org.koin.core.Koin

object Application {

	fun create(config: ApplicationConfig): Instance {
		val dependencies = resolveDependencies(config).koin

		Database.migrateDb(dependencies.get())

		val server = embeddedServer(
			factory = Netty,
			port = config.server.port,
			host = config.server.host,
		) {
			install(CallLogging)
			install(Compression) { gzip() }
			install(ContentNegotiation) { json() }
			install(MicrometerMetrics) { registry = dependencies.get<PrometheusMeterRegistry>() }

			routing {
				DummyJsonRoutes(dependencies.get()).register(this)
				GreetingRoutes(dependencies.get()).register(this)
				MetricRoutes(dependencies.get()).register(this)
				UserRoutes(dependencies.get()).register(this)
			}
		}

		return Instance(server, dependencies)
	}

	data class Instance(
		val server: BaseApplicationEngine,
		val dependencies: Koin
	)

}
