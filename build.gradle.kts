import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "inc.evil"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
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
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("com.tngtech.archunit:archunit-junit5:1.0.0-rc1")
	testImplementation("com.h2database:h2:2.1.214")
	testImplementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")
	testImplementation("org.testcontainers:testcontainers:1.17.3")
	testImplementation("org.testcontainers:postgresql:1.17.3")
	testImplementation("org.springframework.graphql:spring-graphql-test")
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
