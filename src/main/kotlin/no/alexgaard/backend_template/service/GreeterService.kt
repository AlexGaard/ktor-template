package no.alexgaard.backend_template.service

import no.alexgaard.backend_template.repository.GreeterRepository

class GreeterService(
	private val greeterRepository: GreeterRepository
) {

	fun getGreeting(): String {
		return greeterRepository.getStoredGreeting()
	}

}