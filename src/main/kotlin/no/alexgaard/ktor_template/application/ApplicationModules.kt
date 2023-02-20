package no.alexgaard.ktor_template.application

import no.alexgaard.ktor_template.application.Database.createDataSource
import no.alexgaard.ktor_template.application.Database.createJdbi
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.repository.GreeterRepository
import no.alexgaard.ktor_template.repository.UserRepository
import no.alexgaard.ktor_template.service.GreeterService
import no.alexgaard.ktor_template.service.UserService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ApplicationModules {

	fun createModules(config: ApplicationConfig): org.koin.core.module.Module {
		return module {
			single { config }
			single { createDataSource(config.database) }
			singleOf(::createJdbi)
			singleOf(::UserService)
			singleOf(::GreeterService)
			singleOf(::GreeterRepository)
			singleOf(::UserRepository)
		}
	}

}