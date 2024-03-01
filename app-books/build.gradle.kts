plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
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

    runtimeOnly("org.jboss:jandex")
    runtimeOnly("jakarta.activation:jakarta.activation-api")

    // JPA and Hibernate
    implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")
    implementation("org.hibernate:hibernate-core:5.6.1.Final")
    implementation("org.postgresql:postgresql:42.3.1")

    // CDI (Contexts and Dependency Injection)
    implementation("javax.enterprise:cdi-api:2.0")
    implementation("org.eclipse.microprofile.cdi:microprofile-cdi-api:4.0")

    // JTA (Java Transaction API)
    implementation("javax.transaction:javax.transaction-api:1.3")
}

tasks.test {
    useJUnitPlatform()
}