import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	kotlin("jvm") version "1.4.32"
}

group = "com.cubetiqs"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.squareup.okhttp3:okhttp:4.9.0")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// logback driver and slf4j logging
	implementation("ch.qos.logback:logback-core:1.3.0-alpha5")
	implementation("org.slf4j:slf4j-api:2.0.0-alpha1")

	testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

tasks {
	val sourcesJar by creating(Jar::class) {
		dependsOn(JavaPlugin.CLASSES_TASK_NAME)
		from(sourceSets["main"].allSource)
	}

	artifacts {
		add("archives", sourcesJar)
	}
}
