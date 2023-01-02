plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("plugin.serialization") apply false

    id("org.springframework.boot")
    id("io.spring.dependency-management")

    java
}

group = "com.saintbeller96"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "kotlinx-serialization")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        mavenCentral()
        maven("https://repo.spring.io/release")
    }

    val junitVersion = "5.9.0"
    val kotestVersion = "5.5.0"
    val mockkVersion = "1.12.7"

    dependencyManagement {
        imports {
            mavenBom("org.junit:junit-bom:$junitVersion")
            mavenBom("io.kotest:kotest-bom:$kotestVersion")
        }

        dependencies {
            dependency("io.mockk:mockk:$mockkVersion")
        }
    }

    dependencies {
        implementation(kotlin("stdlib"))

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        testImplementation(kotlin("test"))
        testImplementation("io.kotest:kotest-runner-junit5")
        testImplementation("io.kotest:kotest-assertions-core")
        testImplementation("io.kotest:kotest-property")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.jar { enabled = true }
    tasks.bootJar { enabled = false }
}

val springApplications: List<Project> = listOf(
    project(":rest-api"),
    project(":context")
)

configure(springApplications) {
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
}
