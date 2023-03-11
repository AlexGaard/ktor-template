package no.alexgaard.ktor_template.routes

import io.javalin.Javalin
import io.javalin.http.Context
import io.micrometer.prometheus.PrometheusMeterRegistry
import io.prometheus.client.exporter.common.TextFormat

class MetricRoutes(
	private val meterRegistry: PrometheusMeterRegistry
) {

	fun register(app: Javalin) {
		getMetrics(app)
	}

	private fun getMetrics(app: Javalin) {
		app.get("/internal/metrics") { ctx ->
			ctx.contentType(TextFormat.CONTENT_TYPE_004).result(meterRegistry.scrape())
		}
	}

}