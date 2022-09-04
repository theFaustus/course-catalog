import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("com.github.davidmc24.gradle.plugin.avro") version ("1.2.0")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

//buildscript {
//    repositories {
//        maven {
//            url = uri("https://plugins.gradle.org/m2/")
//        }
//    }
//    dependencies {
//        classpath("com.commercehub.gradle.plugin:gradle-avro-plugin:0.99.99")
//    }
//}
//
//apply(plugin = "com.commercehub.gradle.plugin.avro")

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
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
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
    testImplementation("com.tngtech.archunit:archunit-junit5:1.0.0-rc1")
    testImplementation("com.h2database:h2:2.1.214")
    testImplementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")
    testImplementation("org.testcontainers:testcontainers:1.17.3")
    testImplementation("org.testcontainers:postgresql:1.17.3")
    testImplementation("org.springframework.graphql:spring-graphql-test")

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
    println("${projectDir}\\src\\main\\resources\\avro")
    source(file("${projectDir}\\src\\main\\resources\\avro"))
}

avro {
    fieldVisibility.set("private")
    customConversion(org.apache.avro.Conversions.UUIDConversion::class.java)
}


//sourceSets {
//	test {
////		withConvention(KotlinSourceSet::class){
////			kotlin.setSrcDirs(listOf("src/test/integration", "src/test/unit"))
////		}
////		java {
////			setSrcDirs(listOf("src/test/kotlin/inc/evil/coursecatalog/integration", "src/test/kotlin/inc/evil/coursecatalog"))
////		}
//	}
//}
