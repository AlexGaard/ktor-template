package no.alexgaard.ktor_template.test_utils

import java.io.IOException
import java.net.ServerSocket
import kotlin.random.Random

object FreePortFinder {

	fun findFreePort(fromPort: Int = 1025, toPort: Int = 65536): Int {
		while (true) {
			val port = Random.nextInt(fromPort, toPort)
			try {
				ServerSocket(port).use { _ ->
					return port
				}
			} catch (_: IOException) {}
		}
	}

}