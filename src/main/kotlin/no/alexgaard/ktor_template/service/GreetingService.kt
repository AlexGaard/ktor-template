package no.alexgaard.ktor_template.service

class GreetingService {

	fun getGreeting(name: String): String {
		return "Hello $name"
	}

}