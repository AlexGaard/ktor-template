package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusMeterRegistry

fun Route.registerMetricRoutes(meterRegistry: PrometheusMeterRegistry) = route("/internal/metrics") {
	get {
		call.respond(meterRegistry.scrape())
	}
}