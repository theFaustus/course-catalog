import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("com.github.davidmc24.gradle.plugin.avro") version ("1.2.0")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.3.72"
}

group = "inc.evil"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven")
    }
}

dependencies {
    implementation(project(":courses-api"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.hazelcast:hazelcast:5.2.1")
    implementation("com.hazelcast:hazelcast-spring:5.2.1")

    implementation("org.apache.avro:avro:1.11.0") {
        exclude("org.slf4j")
    }
    implementation("org.springframework.kafka:spring-kafka") {
        exclude("org.slf4j")
    }
    implementation("org.apache.kafka:kafka-clients:3.2.1") {
        exclude("org.slf4j")
    }
    implementation("org.apache.kafka:kafka-streams:3.2.1") {
        exclude("org.slf4j")
    }
    implementation("io.confluent:kafka-avro-serializer:5.3.0") {
        exclude("org.slf4j")
    }
    implementation("io.confluent:kafka-streams-avro-serde:7.2.1") {
        exclude("org.slf4j")
    }

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.0.0-rc1")
    testImplementation("com.h2database:h2:2.1.214")
    testImplementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")
    testImplementation("org.testcontainers:testcontainers:1.17.3")
    testImplementation("org.testcontainers:postgresql:1.17.3")
    testImplementation("org.testcontainers:kafka:1.17.3")
    testImplementation("com.github.tomakehurst:wiremock:2.27.2")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask> {
    source(file("${projectDir}\\src\\main\\resources\\avro"))
    setOutputDir(file("${projectDir}\\src\\main\\kotlin"))
}

avro {
    fieldVisibility.set("private")
    customConversion(org.apache.avro.Conversions.UUIDConversion::class.java)
}
