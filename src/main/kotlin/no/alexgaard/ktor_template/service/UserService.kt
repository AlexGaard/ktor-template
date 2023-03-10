package no.alexgaard.ktor_template.service

import no.alexgaard.ktor_template.repository.UserDbo
import no.alexgaard.ktor_template.repository.UserRepository

class UserService(
	private val userRepository: UserRepository
) {

	fun getAllUsers(): List<UserDbo> {
		return userRepository.getAllUsers()
	}

	fun createUser(name: String): UserDbo {
		return userRepository.createUser(name)
	}

}