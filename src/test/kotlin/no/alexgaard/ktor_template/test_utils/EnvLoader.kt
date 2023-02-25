package no.alexgaard.ktor_template.test_utils

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

object EnvLoader {

	fun includeDotenvVariables() {
		val localEnv = dotenv {
			filename = "local.env"
			directory = ".local"
		}

		setProperties(localEnv)

		val overrideEnv = dotenv {
			filename = "override-local.env"
			directory = ".local"
			ignoreIfMissing = true
		}

		setProperties(overrideEnv)
	}

	private fun setProperties(env: Dotenv) {
		env.entries().forEach { System.setProperty(it.key, it.value) }
	}

}
