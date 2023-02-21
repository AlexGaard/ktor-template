package no.alexgaard.ktor_template.test_utils

import java.io.IOException
import java.net.ServerSocket

object FreePortFinder {

	fun findFreePort(fromPort: Int = 1025, toPort: Int = 65536): Int {
		for (port in fromPort until toPort) {
			try {
				ServerSocket(port).use { _ ->
					return port
				}
			} catch (_: IOException) {}
		}

		throw IllegalStateException("No free ports in range $fromPort to $toPort")
	}

}