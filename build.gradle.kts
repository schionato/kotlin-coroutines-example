plugins {
	kotlin("jvm") version "1.9.25"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Kotlin Coroutines example"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
