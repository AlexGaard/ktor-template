package no.alexgaard.ktor_template.routes

import io.javalin.Javalin
import io.javalin.http.Context
import no.alexgaard.ktor_template.service.GreetingService

class GreetingRoutes(
	private val greetingService: GreetingService
) {

	fun register(app: Javalin) {
		getGreeting(app)
	}

	private fun getGreeting(app: Javalin) {
		app.get("/api/v1/greeting") { ctx ->
			val name = ctx.queryParam("name") ?: throw IllegalArgumentException("name is missing")
			ctx.result(greetingService.getGreeting(name))
		}
	}

}
