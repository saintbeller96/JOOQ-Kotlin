import org.jooq.meta.jaxb.Property

plugins {
    id("nu.studer.jooq") version "7.1.1"
}

val jooqVersion = "3.16.4"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.jooq:jooq-kotlin:$jooqVersion")

    runtimeOnly("com.h2database:h2")

    jooqGenerator("com.h2database:h2")
    jooqGenerator("mysql:mysql-connector-java:8.0.30")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
}

jooq {
    version.set(jooqVersion)  // the default (can be omitted)
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)  // the default (can be omitted)
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.h2.Driver"
                    url = "jdbc:h2:~/test;AUTO_SERVER=TRUE"
                    user = "sa"
                    password = "123"
                    properties = listOf(
                        Property().apply {
                            key = "PAGE_SIZE"
                            value = "2048"
                        }
                    )
                }

                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.h2.H2Database"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isImmutablePojos = false
                        isFluentSetters = false
                    }
                    target.apply {
                        packageName = "com.saintbeller96.kotlinjooq"
                        directory = "build/generated-src"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}
