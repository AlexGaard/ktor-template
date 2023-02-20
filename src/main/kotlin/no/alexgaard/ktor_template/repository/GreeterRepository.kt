package no.alexgaard.ktor_template.repository

class GreeterRepository() {

	fun getStoredGreeting(): String {
		return "Hello from repository"
	}

}