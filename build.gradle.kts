val ktorVersion = "2.2.3"
val kotlinVersion = "1.8.10"
val logbackVersion = "1.2.11"

plugins {
	kotlin("jvm") version "1.8.10"
	id("io.ktor.plugin") version "2.2.3"
}

group = "no.alexgaard"
version = "0.0.1"

application {
	mainClass.set("no.alexgaard.ApplicationKt")

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
	testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}