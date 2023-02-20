package no.alexgaard.ktor_template.service

import no.alexgaard.ktor_template.repository.UserDbo
import no.alexgaard.ktor_template.repository.UserRepository

class UserService(
	private val userRepository: UserRepository
) {

	fun getAllUsers(): List<UserDbo> {
		return userRepository.getAllUsers()
	}

}