package no.alexgaard.ktor_template.application

import io.javalin.micrometer.MicrometerPlugin
import io.micrometer.core.instrument.MeterRegistry

object Metrics {

	fun micrometerPlugin(meterRegistry: MeterRegistry): MicrometerPlugin {
		return MicrometerPlugin.Companion.create { micrometerConfig ->
			micrometerConfig.registry = meterRegistry
		}
	}

}