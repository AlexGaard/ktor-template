val javalinVersion = "5.4.2"
val jacksonVersion = "2.14.2"
val kotlinVersion = "1.8.10"
val logbackVersion = "1.4.5"
val sl4jVersion = "2.0.6"
val hopliteVersion = "2.7.1"
val koinVersion = "3.3.3"
val junitVersion = "5.8.1"
val jdbiVersion = "3.37.1"
val flywayVersion = "9.15.0"
val hikariVersion = "5.0.1"
val postgresVersion = "42.5.4"
val testcontainersVersion = "1.17.6"
val kotestVersion = "5.5.5"
val okHttpVersion = "4.10.0"
val prometheusVersion = "1.10.4"
val dotenvVersion = "6.3.1"

plugins {
	application
	kotlin("jvm") version "1.8.10"
}

group = "no.alexgaard"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.javalin:javalin:$javalinVersion")
	implementation("io.javalin:javalin-micrometer:$javalinVersion")

	implementation("ch.qos.logback:logback-core:$logbackVersion")
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	implementation("org.slf4j:slf4j-api:$sl4jVersion")

	implementation("com.sksamuel.hoplite:hoplite-core:$hopliteVersion")
	implementation("com.sksamuel.hoplite:hoplite-yaml:$hopliteVersion")

	implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
	implementation("io.micrometer:micrometer-registry-prometheus:$prometheusVersion")
	implementation("io.insert-koin:koin-core:$koinVersion")
	implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

	implementation("org.jdbi:jdbi3-core:$jdbiVersion")
	implementation("org.jdbi:jdbi3-kotlin:$jdbiVersion")
	implementation("org.flywaydb:flyway-core:$flywayVersion")
	implementation("com.zaxxer:HikariCP:$hikariVersion")

	runtimeOnly("org.postgresql:postgresql:$postgresVersion")

	testImplementation("io.github.cdimascio:dotenv-kotlin:$dotenvVersion")
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

application {
	mainClass.set("no.alexgaard.ktor_template.MainKt")
}

tasks.register<Jar>("fatJar") {
	dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources"))
	archiveFileName.set("app.jar")
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE

	manifest { attributes(mapOf("Main-Class" to application.mainClass)) }

	val sourcesMain = sourceSets.main.get()
	val contents = configurations.runtimeClasspath.get()
		.map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output

	from(contents)
}

tasks.build {
	dependsOn(tasks["fatJar"])
}
