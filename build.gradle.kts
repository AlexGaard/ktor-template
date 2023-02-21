val ktorVersion = "2.2.3"
val kotlinVersion = "1.8.10"
val logbackVersion = "1.2.11"
val hopliteVersion = "2.7.1"
val koinVersion = "3.3.3"
val junitVersion = "5.8.1"
val jdbiVersion = "3.37.1"
val flywayVersion = "9.15.0"
val hikariVersion = "5.0.1"
val postgresVersion = "42.5.4"
val testcontainersVersion = "1.17.6"
val kotestVersion = "5.5.5"

plugins {
	kotlin("jvm") version "1.8.10"
	id("io.ktor.plugin") version "2.2.3"
}

group = "no.alexgaard"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

application {
	mainClass.set("no.alexgaard.ktor_template.MainKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
	implementation("ch.qos.logback:logback-classic:$logbackVersion")

	implementation("com.sksamuel.hoplite:hoplite-core:$hopliteVersion")
	implementation("com.sksamuel.hoplite:hoplite-yaml:$hopliteVersion")
	implementation("io.insert-koin:koin-core:$koinVersion")

	implementation("org.jdbi:jdbi3-core:$jdbiVersion")
	implementation("org.jdbi:jdbi3-kotlin:$jdbiVersion")
	implementation("org.flywaydb:flyway-core:$flywayVersion")
	implementation("com.zaxxer:HikariCP:$hikariVersion")

	runtimeOnly("org.postgresql:postgresql:$postgresVersion")

	testImplementation("io.insert-koin:koin-test:$koinVersion")
	testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
	testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
	testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}