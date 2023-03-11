package no.alexgaard.ktor_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusMeterRegistry

class MetricRoutes(
	private val meterRegistry: PrometheusMeterRegistry
) {

	fun register(routing: Routing) {
		routing.get("/internal/metrics") {
			call.respond(meterRegistry.scrape())
		}
	}

}
