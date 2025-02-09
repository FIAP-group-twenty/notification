import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "6.0.1.5171"
    id("jacoco")
}

group = "br.com.soat"
version = "0.0.1-SNAPSHOT"

tasks.jar {
    manifest {
        attributes["Main-Class"] = " br.com.soat.notification.NotificationApplication"
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

jacoco {
    toolVersion = "0.8.8"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")

    //Test
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.springframework:spring-context-support:6.0.11")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-beans:6.0.11")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

sonarqube {
    properties {
        property ("sonar.projectKey", "group-twenty_notification-service")
        property ("sonar.organization", "group-twenty")
        property ("sonar.host.url", project.findProperty("SONAR_HOST_URL") ?: "")
        property("sonar.login", project.findProperty("SONAR_TOKEN") ?: "")
        property("sonar.kotlin.language.level", "1.9")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.tests", "src/test/kotlin")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()

    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        events("passed", "failed", "skipped")
    }
    reports {
        junitXml.required.set(true)
        junitXml.outputLocation.set(file("${project.projectDir}/test-results/test"))
        html.required.set(true)
        html.outputLocation.set(file("${project.projectDir}/test-results/test"))
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(file("${project.projectDir}/reports/jacoco"))
    }
}
