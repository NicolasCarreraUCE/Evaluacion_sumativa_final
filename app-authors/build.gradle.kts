plugins {
    id("java")
    id("org.kordamp.gradle.jandex") version "0.6.0"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val helidonversion = " 4.0.5"

dependencies {
    implementation(enforcedPlatform("io.helidon:helidon-dependencies:${helidonversion}"))
    implementation("io.helidon.microprofile.bundles:helidon-microprofile")
    implementation("org.glassfish.jersey.media:jersey-media-json-binding")

    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-jpa")
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-jta-weld")
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp")
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-hibernate:2.0.0")
    implementation("org.postgresql:postgresql:42.2.7")

    // https://mvnrepository.com/artifact/jakarta.enterprise/jakarta.enterprise.cdi-api
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0-M1")

    // https://mvnrepository.com/artifact/io.helidon.microprofile.jwt/helidon-microprofile-jwt-auth
    implementation("io.helidon.microprofile.jwt:helidon-microprofile-jwt-auth:4.0.5")


    runtimeOnly("org.jboss:jandex")
    runtimeOnly("jakarta.activation:jakarta.activation-api")
}

tasks.test {
    useJUnitPlatform()
}
