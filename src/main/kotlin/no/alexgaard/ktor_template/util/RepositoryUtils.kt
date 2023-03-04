package no.alexgaard.ktor_template.util

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi

fun <T> Jdbi.handle(fn: (handle: Handle) -> T): T {
	return this.withHandle<T, Exception>(fn)
}
