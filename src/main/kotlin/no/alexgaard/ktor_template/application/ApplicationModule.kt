package no.alexgaard.ktor_template.application

import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import no.alexgaard.ktor_template.application.Database.createDataSource
import no.alexgaard.ktor_template.application.Database.createJdbi
import no.alexgaard.ktor_template.client.dummy_json.DummyJsonClient
import no.alexgaard.ktor_template.client.dummy_json.DummyJsonClientImpl
import no.alexgaard.ktor_template.config.ApplicationConfig
import no.alexgaard.ktor_template.repository.UserRepository
import no.alexgaard.ktor_template.service.GreeterService
import no.alexgaard.ktor_template.service.UserService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ApplicationModule {

	fun createModule(config: ApplicationConfig): org.koin.core.module.Module {
		return module {
			single { config }
			single { createDataSource(config.database) }
			single { PrometheusMeterRegistry(PrometheusConfig.DEFAULT) }
			single { DummyJsonClientImpl(config.dummyJsonClient.baseUrl, { "DUMMY TOKEN" }) as DummyJsonClient }
			singleOf(::createJdbi)
			singleOf(::UserService)
			singleOf(::GreeterService)
			singleOf(::UserRepository)
		}
	}

}