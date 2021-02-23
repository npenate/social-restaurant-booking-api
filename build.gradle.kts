import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
	id("io.gitlab.arturbosch.detekt") version "1.0.1"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.3.71"
}

group = "com.nelo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	jcenter()
}

val springBootVersion = "2.2.6.RELEASE"
val kotlinVersion = "1.3.72"
val detektVersion = "1.0.0-RC16"
val jaxbVersion by extra { "2.3.1" }
val arrowVersion = "0.8.2"

dependencies {
	// Kotlin and libraries
	implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
	implementation("io.arrow-kt:arrow-core:$arrowVersion")

	// Web
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
	implementation("org.apache.httpcomponents:httpclient:4.5.12")

	// Data
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
	implementation("org.springframework.data:spring-data-jpa:$springBootVersion")
	implementation("org.postgresql:postgresql:42.2.12.jre7")
	implementation("org.flywaydb:flyway-core:6.0.8")

	// Documentation
	implementation("org.springdoc:springdoc-openapi-ui:1.2.32")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "junit")
		exclude(module = "mockito-core")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
	testImplementation("com.ninja-squad:springmockk:1.1.3")

	// Detekt
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

detekt {
	toolVersion = detektVersion
	config = files("./detekt-config.yml")
	autoCorrect = true
}

