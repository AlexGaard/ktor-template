package no.alexgaard.ktor_template.application

import io.javalin.Javalin
import io.micrometer.prometheus.PrometheusMeterRegistry
import no.alexgaard.ktor_template.application.ApplicationModule.resolveDependencies
import no.alexgaard.ktor_template.application.Metrics.micrometerPlugin
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.routes.DummyJsonRoutes
import no.alexgaard.ktor_template.routes.GreetingRoutes
import no.alexgaard.ktor_template.routes.MetricRoutes
import no.alexgaard.ktor_template.routes.UserRoutes
import org.eclipse.jetty.server.Server
import org.koin.core.Koin
import java.net.InetSocketAddress

object Application {
	fun create(config: ApplicationConfig): Instance {
		val dependencies = resolveDependencies(config).koin

		Database.migrateDb(dependencies.get())

		val app = Javalin.create {
			it.showJavalinBanner = false
			it.compression.gzipOnly()
			it.jetty.server { Server(InetSocketAddress(config.server.host, config.server.port)) }
			it.plugins.enableDevLogging()
			it.plugins.register(micrometerPlugin(dependencies.get<PrometheusMeterRegistry>()))
		}

		DummyJsonRoutes(dependencies.get()).register(app)
		GreetingRoutes(dependencies.get()).register(app)
		MetricRoutes(dependencies.get()).register(app)
		UserRoutes(dependencies.get()).register(app)

		return Instance(app, dependencies)
	}

	data class Instance(
		val server: Javalin,
		val dependencies: Koin
	)
}

