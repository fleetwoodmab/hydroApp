import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
}

group = "me.petit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // dependencies for logging
    implementation("io.github.microutils:kotlin-logging:2.1.21")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}