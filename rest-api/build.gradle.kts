dependencies {
    implementation(project(":context"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.bootJar {
    enabled = true
    archiveFileName.set("$name.jar")
}
