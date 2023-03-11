package no.alexgaard.ktor_template.test_utils.database

import org.jdbi.v3.core.Jdbi
import javax.sql.DataSource

object DatabaseUtils {

	private const val DEFAULT_SCHEMA = "public"

	private const val FLYWAY_TABLE_NAME = "flyway_schema_history"

	fun isValid(dataSource: DataSource): Boolean {
		return try {
			dataSource.connection.isValid(10)
		} catch (t: Throwable) {
			false
		}
	}

	fun cleanSchema(jdbi: Jdbi, schema: String = DEFAULT_SCHEMA) {
		val tables = getAllTables(jdbi, schema).filter { it != FLYWAY_TABLE_NAME }

		val sequences = getAllSequences(jdbi, schema)

		jdbi.useHandle<Exception> { handle ->
			tables.forEach {
				handle.createUpdate("TRUNCATE TABLE $it CASCADE").execute()
			}

			sequences.forEach {
				handle.createUpdate("ALTER SEQUENCE $it RESTART WITH 1").execute()
			}
		}
	}

	private fun getAllTables(jdbi: Jdbi, schema: String): List<String> {
		val sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = :schema"

		return jdbi.withHandle<List<String>, Exception> {
			it.createQuery(sql)
				.bind("schema", schema)
				.map { rs, _ -> rs.getString(1) }.toList()
		}
	}

	private fun getAllSequences(jdbi: Jdbi, schema: String): List<String> {
		val sql = "SELECT sequence_name FROM information_schema.sequences WHERE sequence_schema = :schema"

		return jdbi.withHandle<List<String>, Exception> {
			it.createQuery(sql)
				.bind("schema", schema)
				.map { rs, _ -> rs.getString(1) }.toList()
		}
	}

}