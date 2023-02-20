package no.alexgaard.backend_template.application

import no.alexgaard.backend_template.config.ApplicationConfig
import no.alexgaard.backend_template.repository.GreeterRepository
import no.alexgaard.backend_template.service.GreeterService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ApplicationModules {

	fun createModules(_config: ApplicationConfig): org.koin.core.module.Module {
		return module {
			singleOf(::GreeterRepository)
			singleOf(::GreeterService)
		}
	}

}