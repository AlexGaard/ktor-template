package no.alexgaard.ktor_template

import no.alexgaard.ktor_template.test_utils.EnvLoader.includeDotenvVariables

fun main() {
	includeDotenvVariables()

	startApplication()
}