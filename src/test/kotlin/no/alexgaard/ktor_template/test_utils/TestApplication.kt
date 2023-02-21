package no.alexgaard.ktor_template.test_utils

import no.alexgaard.ktor_template.application.ApplicationModule
import no.alexgaard.ktor_template.test_utils.database.DatabaseUtils
import org.junit.jupiter.api.BeforeEach
import org.koin.core.Koin
import org.koin.dsl.koinApplication

open class TestApplication {

	val config = TestApplicationConfig.createTestConfig()

	val koin: Koin

	init {
		val module = ApplicationModule.createModule(config)
		koin = koinApplication { modules(module) }.koin
		DatabaseUtils.cleanAndApplyMigrations(koin.get())
	}

	@BeforeEach
	fun cleanDatabase() {
		DatabaseUtils.cleanSchema(koin.get())
	}

}