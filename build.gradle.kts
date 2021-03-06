import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`java-library`
	kotlin("jvm") version "1.4.32"
}

group = "com.cubetiqs"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// http client
	implementation("com.squareup.okhttp3:okhttp:4.9.0")

	// logback driver and slf4j logging
	implementation("org.slf4j:slf4j-api:1.7.30")
	implementation("org.slf4j:slf4j-simple:1.7.30")

	// test framework
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

tasks.withType<Jar> {
	manifest {
		attributes(mapOf("Implementation-Title" to project.name,
			"Implementation-Version" to project.version))
	}

	from(sourceSets.main.get().output)

	from({
		configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
	})
}