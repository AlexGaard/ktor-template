package no.alexgaard.ktor_template.service

class GreeterService {

	fun getGreeting(name: String): String {
		return "Hello $name"
	}

}