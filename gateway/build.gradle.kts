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
    // Helidon
    implementation(enforcedPlatform("io.helidon:helidon-dependencies:${helidonversion}")) // Helidon dependencias base
    implementation("io.helidon.microprofile.bundles:helidon-microprofile") // Helidon MicroProfile bundle
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-jpa") // Integración CDI para JPA
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-jta-weld") // Integración CDI para JTA con Weld
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp") // Integración CDI para HikariCP
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-hibernate:2.0.0") // Integración CDI para Hibernate
    implementation("io.helidon.microprofile.jwt:helidon-microprofile-jwt-auth:4.0.5") // Autenticación JWT para MicroProfile

    // Dependencias de Jersey
    implementation("org.glassfish.jersey.media:jersey-media-json-binding") // Jersey JSON Binding para serialización/deserialización JSON

    // Dependencias de CDI
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0-M1") // CDI API

    // Dependencias de PostgreSQL
    implementation("org.postgresql:postgresql:42.2.7") // PostgreSQL JDBC driver

    // Dependencias de Jandex
    runtimeOnly("org.jboss:jandex") // Jandex para indexación de clases en tiempo de ejecución

    // Dependencias de Activación Jakarta
    runtimeOnly("jakarta.activation:jakarta.activation-api") // API de activación Jakarta

    // Dependencias de MicroProfile
    implementation("org.eclipse.microprofile.config:microprofile-config-api") // MicroProfile Config API
    implementation("org.eclipse.microprofile.rest.client:microprofile-rest-client-api") // MicroProfile Rest Client API
    implementation("io.helidon.microprofile.health:helidon-microprofile-health:4.0.5") // MicroProfile Health API
    implementation("io.helidon.microprofile.openapi:helidon-microprofile-openapi:4.0.5") // MicroProfile OpenAPI API

}

tasks.test {
    useJUnitPlatform()
}
