plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.auth)

    implementation(libs.ktor.serialization)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}