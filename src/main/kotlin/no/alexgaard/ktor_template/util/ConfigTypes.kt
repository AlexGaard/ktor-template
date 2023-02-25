package no.alexgaard.ktor_template.util

@JvmInline
value class Secret(val value: String) {
	override fun toString(): String {
		return "<redacted>"
	}
}
