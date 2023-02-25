# Ktor Template

A simple application using different technologies to configure and run a more or less production ready setup with Ktor.

## Prerequisites
* A working Docker environment with Docker Compose installed
* Java 17 JDK

## How to run

1. Start the postgres database with Docker compose `docker compose up -d`
2. Run `main()` inside **src/test/kotlin/no/alexgaard/ktor_template/Local.kt**

## Technologies used
* Docker - https://docs.docker.com/
* Docker Compose - https://docs.docker.com/compose/
* Flyway - https://flywaydb.org/
* Gradle - https://gradle.org/
* Hoplite - https://github.com/sksamuel/hoplite
* Jdbi - https://jdbi.org/
* Koin - https://insert-koin.io/
* Ktor - https://ktor.io/
* Postgresql - https://www.postgresql.org/
* Testcontainers - https://www.testcontainers.org/
