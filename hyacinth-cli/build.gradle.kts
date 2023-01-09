plugins {
    application
}

application {
    mainClass.set("com.github.xini1.hyacinth.Main")
}

dependencies {
    implementation(libs.gson)
    implementation(project(":hyacinth-core"))

    testCompileOnly(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.assertj)
}

tasks.register<Jar>("fatJar") {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
    archiveClassifier.set("fat")
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(
            configurations.runtimeClasspath.get()
                    .filter { it.name.endsWith("jar") }
                    .map { zipTree(it) }
    )
}

tasks.test {
    useJUnitPlatform()
}