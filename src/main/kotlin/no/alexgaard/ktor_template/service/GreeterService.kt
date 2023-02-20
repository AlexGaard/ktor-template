package no.alexgaard.ktor_template.service

import no.alexgaard.ktor_template.repository.GreeterRepository

class GreeterService(
	private val greeterRepository: GreeterRepository
) {

	fun getGreeting(): String {
		return greeterRepository.getStoredGreeting()
	}

}